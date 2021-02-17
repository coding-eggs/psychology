package com.lby.psychology.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.*;

@Api
@RestController
public class TestController {


    @GetMapping("/test/hello")
    public String hello() throws Exception {
        if(true){
            throw new Exception("错误了");
        }
        return "hello1";
    }


    private static final List<Integer> list = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {

        final CountDownLatch countDownLatch = new CountDownLatch(100000);


        ExecutorService executorService = Executors.newCachedThreadPool();
        Semaphore semaphore = new Semaphore(1);
        long a = System.currentTimeMillis();
        for(int i = 0;i<100000;i++){
            Future<Integer> f = executorService.submit(() -> {
                try {
                    semaphore.acquire();
                    add();
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
                return 1;
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        System.out.println(list.size());



        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 10000,
                60L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>());




        System.out.println(System.currentTimeMillis() - a);
    }


    public static void  add() throws InterruptedException {
        list.add(1);
    }


}
