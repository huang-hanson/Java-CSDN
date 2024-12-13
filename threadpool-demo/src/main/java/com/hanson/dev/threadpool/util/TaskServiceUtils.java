package com.hanson.dev.threadpool.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName TaskServiceUtils
 * @date 2024/12/13 14:40
 **/
@Slf4j
public class TaskServiceUtils {


    /**
     *
     * @param <T>
     * @param future
     * @param timeout
     * @param timeUnit
     * @return
     */
    public static <T> T futureGet(Future<T> future, long timeout, TimeUnit timeUnit){
        try{
            //调用并返回
            T result=future.get(timeout,timeUnit);
            return result;
        }catch (Exception e){
            log.error("futureGet,e:",e);
            return null;
        }
    }

    /**
     * 将每个list future返回的list去空合并为一个list
     * @param listFutureList
     * @param timeout
     * @param timeUnit
     * @param <T>
     * @return
     */
    public static <T> List<T> futureListGet(List<Future<List<T>>> listFutureList, long timeout, TimeUnit timeUnit){
        return  listFutureList
                .stream()
                .map(i->{ return futureGet(i,timeout,timeUnit); })
                .filter(i-> null != i)
                .flatMap(Collection::stream)
                .distinct()
                .collect(Collectors.toList());
    }

    /**
     * 并列取
     * @param futureList
     * @param timeout
     * @param timeUnit
     * @param <T>
     */
    public static <T> void listFutureGet(List<Future> futureList, long timeout, TimeUnit timeUnit){
        if(CollectionUtils.isEmpty(futureList)){
            return;
        }
        futureList.stream().forEach(i->{
            if(i== null){
                return;
            }
            futureGet(i,timeout,timeUnit);});
    }

    /**
     *
     * @param <T>
     * @param future
     * @param timeout
     * @param timeUnit
     * @param errorDesc
     * @return
     */
    public static <T> T futureGet(Future<T> future, long timeout, TimeUnit timeUnit,String errorDesc){
        try{
            //调用并返回
            T result=future.get(timeout,timeUnit);
            return result;
        }catch (Exception e){
            log.error("futureGet:{},e:",errorDesc,e);
            throw new RuntimeException(errorDesc);
        }
    }

    /**
     * 拆成指定大小的组，拆后的分组数由程序计算
     */
    public static <T> List<List<T>> splitList(List<T> sourceList, int groupSize){
        //结果
        List<List<T>> resultList=new LinkedList<>();
        //空判断，当list大小小于每组大小时，直接返回
        if(CollectionUtils.isEmpty(sourceList) || groupSize<=0|| sourceList.size() <= groupSize){
            resultList.add(sourceList);
            return resultList;
        }
        //分组计算
        int groups=(sourceList.size()-1)/groupSize+1;
        //循环处理
        for(int i=0;i<groups;i++){
            int start=i*groupSize;
            int end= Math.min((i + 1) * groupSize, sourceList.size());
            resultList.add(sourceList.subList(start,end));
        }
        return resultList;
    }

    /**
     * 将一组数据平均分成n组
     * @param source 要分组的数据源
     * @param n      平均分成n组
     * @param <T>
     * @return
     */
    public static <T> List<List<T>> averageAssign(List<T> source, int n) {
        List<List<T>> result = new ArrayList<List<T>>();
        int remainder = source.size() % n;  //(先计算出余数)
        int number = source.size() / n;  //然后是商
        int offset = 0;//偏移量
        for (int i = 0; i < n; i++) {
            List<T> value = null;
            if (remainder > 0) {
                value = source.subList(i * number + offset, (i + 1) * number + offset + 1);
                remainder--;
                offset++;
            } else {
                value = source.subList(i * number + offset, (i + 1) * number + offset);
            }
            result.add(value);
        }
        return result;
    }

}
