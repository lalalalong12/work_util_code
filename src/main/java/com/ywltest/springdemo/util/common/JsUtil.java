package com.ywltest.springdemo.util.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.StringReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 秦菊
 * @version 1.0
 * @date 2021/3/22 14:17
 */

public class JsUtil {
    /**
     *得到脚本引擎
     */
    private  static ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");

    /**
     * js方法名正则
     */
    private static Pattern REGEX = Pattern.compile("[\\s\\S]*function\\s+([^\\(]+)+\\(([^)]*)\\)");

    private static final Logger logger=LoggerFactory.getLogger(JsUtil.class);


    /**
     * js脚本处理
     *
     * @param jsContent:
     * @param data:
     * @return Object
     * @author 秦菊
     * @date 2020/10/20
     */
    public static Object jsDispose(String jsContent, Object data) throws Exception {

        if (jsContent == null) {
            return null;
        }

        //脚本的执行结果
        Object scriptResult;
        //方法名
        String functionName;

        Matcher matcher = REGEX.matcher(jsContent);
        if (matcher.find()) {
            functionName = matcher.group(1);
        } else {
            throw new Exception("函数格式错误");
        }
        try {
            //1.引擎读取 脚本字符串
            engine.eval(new StringReader(jsContent));
        } catch (ScriptException e) {
            logger.warn("\n***协议脚本转换失败\n***原因：引擎读取脚本字符串失败【{}】\n***jsContent:【{}】\n***data:【{}】",e.getMessage(),jsContent,data);
            logger.debug("\n***协议脚本转换失败【{}】",e);
            return null;
        }
        //2.将引擎转换为Invocable，这样才可以调用js的方法
        Invocable invocable = (Invocable) engine;
        try {
            //3.使用 invocable.invokeFunction调用js脚本里的方法，第一個参数为方法名，后面的参数为被调用的js方法的入参
            scriptResult = invocable.invokeFunction(functionName, data);
            return scriptResult;
        } catch (ScriptException | NoSuchMethodException e) {
            logger.warn("\n***协议脚本转换失败\n***原因：脚本处理失败【{}】\n***jsContent:【{}】\n***data:【{}】",e.getMessage(),jsContent,data);
            logger.debug("\n***协议脚本转换失败【{}】",e);
            return null;
        }
    }
}
