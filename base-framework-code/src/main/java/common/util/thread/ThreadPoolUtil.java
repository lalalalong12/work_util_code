package common.util.thread;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 *@ClassName ThreadPoolUtil
 *@Description  线程池工厂
 *@Author yangwenlong
 *@Date 2020/12/2 16:18
 *@Version 1.0
 */
public class ThreadPoolUtil {
    /**
     * 单例模式
     */
    private ThreadPoolUtil() {
    }


    /**
     * 获取单例
     *
     * @return
     */
    public static ThreadPoolUtil getInstance() {
        return ThreadPoolFactorySingle.INSTANCE.getInstance();
    }

    /**
     * 枚举创建单例
     */
    private enum ThreadPoolFactorySingle {
        INSTANCE;

        private ThreadPoolUtil threadPoolFactoryInstance;

        ThreadPoolFactorySingle() {
            threadPoolFactoryInstance = new ThreadPoolUtil();
        }

        public ThreadPoolUtil getInstance() {
            return threadPoolFactoryInstance;
        }
    }


    /**
     * 默认获取系统空闲的线程数
     */
    private static final int THREAD_COUNTS = Runtime.getRuntime().availableProcessors();

    /**
     * 默认线程池名称
     */
    private static final String FACTORY_NAME = "TOS_THREAD_POOL-%d";

    /**
     * 默认队列大小
     */
    private static final int QUEUE_SIZE = 1024;

    /**
     * 默认有界队列
     */
    private static final BlockingQueue<Runnable> TASK_QUEUE = new ArrayBlockingQueue<>(QUEUE_SIZE);
    /**
     * 默认线程池,固定大小，有界队列
     */
    private static final ThreadPoolExecutor DEFAULT_THREAD_POOL_EXECUTOR =
            new ThreadPoolExecutor(THREAD_COUNTS + 5, THREAD_COUNTS * 5, 60,
                    TimeUnit.SECONDS, TASK_QUEUE,
                    new ThreadFactoryBuilder().setNameFormat(FACTORY_NAME).build(),
                    new ThreadPoolExecutor.CallerRunsPolicy());
    /**
     * 自定义线程池
     */
    private static ThreadPoolExecutor THREAD_POOL_EXECUTOR = null;


    /**
     * 创建默认线程池
     *
     * @return
     */
    public ExecutorService defaultPool() {
        return DEFAULT_THREAD_POOL_EXECUTOR;
    }

}


