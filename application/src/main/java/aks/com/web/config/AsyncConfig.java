package aks.com.web.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.Arrays;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * 异步任务配置
 * 支持传统线程池和虚拟线程两种模式
 * @author 34011
 */
@Slf4j
@Configuration
@EnableAsync
public class AsyncConfig implements AsyncConfigurer {

    /**
     * 默认异步执行器（传统线程池）
     */
    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(10);
        executor.setQueueCapacity(25);
        executor.setThreadNamePrefix("DefaultAsync-");
        executor.initialize();
        return executor;
    }

    /**
     * 虚拟线程执行器
     * 使用方式: @Async("virtualThreadExecutor")
     */
    @Bean(name = "virtualThreadExecutor")
    public Executor virtualThreadExecutor() {
        return Executors.newVirtualThreadPerTaskExecutor();
    }

    /**
     * 传统线程池执行器（I/O密集型任务）
     * 使用方式: @Async("ioThreadPoolExecutor")
     */
    @Bean(name = "ioThreadPoolExecutor")
    public AsyncTaskExecutor ioThreadPoolExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // I/O密集型任务通常设置较大的线程数
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(50);
        executor.setQueueCapacity(100);
        executor.setThreadNamePrefix("IOAsync-");
        executor.initialize();
        return executor;
    }

    /**
     * 传统线程池执行器（CPU密集型任务）
     * 使用方式: @Async("cpuThreadPoolExecutor")
     */
    @Bean(name = "cpuThreadPoolExecutor")
    public AsyncTaskExecutor cpuThreadPoolExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // CPU密集型任务通常设置为CPU核心数+1
        int processors = Runtime.getRuntime().availableProcessors();
        executor.setCorePoolSize(processors);
        executor.setMaxPoolSize(processors * 2);
        executor.setQueueCapacity(50);
        executor.setThreadNamePrefix("CPUAsync-");
        executor.initialize();
        return executor;
    }

    /**
     * 异步任务异常处理器
     */
    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return (ex, method, params) -> {
            log.error("异步任务执行异常 - 方法: {}, 参数: {}", method.getName(), Arrays.toString(params), ex);
        };
    }
}
