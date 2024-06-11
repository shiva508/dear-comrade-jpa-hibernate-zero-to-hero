package com.comrade.service;

import com.comrade.entity.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Test {
    public static void main(String[] args) {

        ForkJoinPool forkJoinPool = new ForkJoinPool(10);
        List<Task> taskSet = IntStream.range(1, 1000).mapToObj(value -> new Task("NAME_"+value, "TASK_"+value)).collect(Collectors.toList());
        AtomicInteger atomicInteger = new AtomicInteger(0);
        List<List<Task>> listList = new ArrayList<>(taskSet.stream().collect(Collectors.groupingBy(task -> atomicInteger.incrementAndGet()/100)).values());
        List<ForkJoinTask<?>> forkJoinTasks = new ArrayList<>();
        listList.forEach(tasks -> {
            try {
                forkJoinPool.submit(()->{
                    tasks.parallelStream().forEach(task ->{
                        task.setTaskName(task.getTaskName()+"A");
                        System.out.println("============="+task.getTaskName()+"  "+task.getName());
                    });
                }).get();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }

        });
        forkJoinTasks.parallelStream().forEach(forkJoinTask -> forkJoinTask.invoke());

    }

    private static void extracted() {
        ForkJoinPool forkJoinPool = new ForkJoinPool(10);
        List<Task> taskSet = IntStream.range(1, 1000).mapToObj(value -> new Task("NAME_"+value, "TASK_"+value)).collect(Collectors.toList());
        AtomicInteger atomicInteger = new AtomicInteger(0);
        List<List<Task>> listList = new ArrayList<>(taskSet.stream().collect(Collectors.groupingBy(task -> atomicInteger.incrementAndGet()/100)).values());
        List<ForkJoinTask<?>> forkJoinTasks = new ArrayList<>();
        listList.forEach(tasks -> {
            ForkJoinTask<?> forkJoinTask = forkJoinPool.submit(()->{
                tasks.parallelStream().forEach(task ->{
                    task.setTaskName(task.getTaskName()+"A");
                    System.out.println("============="+task.getTaskName()+"  "+task.getName());
                });
            });
            forkJoinTasks.add(forkJoinTask);
        });
        forkJoinTasks.parallelStream().forEach(forkJoinTask -> forkJoinTask.invoke());
    }
}
