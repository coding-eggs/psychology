package com.lby.psychology.controller;

import com.lby.psychology.model.ResponseData;
import com.lby.psychology.model.vo.PsycUploadVo;
import io.swagger.annotations.Api;
import lombok.Data;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.script.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

@Api
@RestController
@RequestMapping("/test")
public class TestController {


//    public static void main(String[] args) throws InterruptedException, ClassNotFoundException {
//
//
//        ScriptEngine engine = new ScriptEngineManager().getEngineByName("javascript");
//        Compilable compilable = (Compilable) engine;
//        Bindings bindings = engine.createBindings(); //Local级别的Binding
//        String script = "basicGrowth +  continuousGrowth*3"; //定义函数并调用
//        CompiledScript JSFunction = null; //解析编译脚本函数
//        try {
//            JSFunction = compilable.compile(script);
//            bindings.put("basicGrowth", 100);
//            bindings.put("continuousGrowth", 50);
//            Object result = JSFunction.eval(bindings);
//            System.out.println(result); //调用缓存着的脚本函数对象，Bindings作为参数容器传入
//        } catch (ScriptException e) {
//            e.printStackTrace();
//        }
//    }



    @PostMapping(value = "/uploadFile",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseData<String> uploadFile(PsycUploadVo vo) throws IOException {

        String [] strings = vo.getRelativePath().split("/");

        if (strings.length > 1 ) {
            File file1 = new File("C:/Users/coding/Pictures/" + strings[0]);
            file1.mkdir();
        }

        FileOutputStream outputStream = new FileOutputStream("C:/Users/coding/Pictures/"+vo.getRelativePath());
        outputStream.write(vo.getFile().getBytes());


        outputStream.close();

        return new ResponseData<>(vo.getFile().getOriginalFilename());
    }


    /**
     * 18:49
     * Ares
     * 编程题（1）
     * 实现下面的函数：
     * // 从一个双向链表中删除符合指定值的节点
     * Node deleteVal(Node head,int val){
     * }
     *
     * public class Node{
     * 	public int val; // 节点值
     * 	public Node next; // 指向下一个节点
     * 	public Node pre; // 指向上一个节点
     * }
     *
     * Ares
     * 编程题（2）
     * 对于整形数组(int[]), 我们定义其中任何连续的一段子集为子数组（类似字符串的子串），比如:对于[1,8,3,9,2],则[1],[8,3,9]等都是子数组。
     * 对于每个子数组，将其中所有元素相加所得的和，定义为元素和。
     * 请写一个函数，对于输入的int[],输出其中元素和最大的子数组（如果有多个元素和一样，输出其中任何一个即可）
     *
     */







    @Data
     public static class Node{
     	public int val; // 节点值
     	public Node next; // 指向下一个节点
     	public Node pre; // 指向上一个节点

        public Node (int val) {
            this.val = val;
        }
     }


     static Node deleteVal(Node head,int val){
         if (head == null) return null;
         Node result = head;
         while (head != null) {
             if(head.val == val) {
                 if(head.pre == null) {
                     result = head.next;
                 }else {
                     Node pre = head.pre;
                     head.pre.next = head.next;
                     if (head.next != null) {
                         head.next.pre = pre;
                     }
                 }
             }
             head = head.next;
         }
         return result;
     }


    /**
     *
     * 对于整形数组(int[]), 我们定义其中任何连续的一段子集为子数组（类似字符串的子串），比如:对于[1,8,3,9,2],则[1],[8,3,9]等都是子数组。
     * 对于每个子数组，将其中所有元素相加所得的和，定义为元素和。
     * 请写一个函数，对于输入的int[],输出其中元素和最大的子数组（如果有多个元素和一样，输出其中任何一个即可）
     */
    public static int[] maxSumArray(int [] array) {

        if (array.length == 0) return null;
        int max = array[0];
        int [] result = {array[0]};
        for (int i = 0; i < array.length; i++) {
            for (int j = i; j < array.length; j++) {
                int sum = 0;
                int k = i;
                while (k <= j){
                    sum = sum + array[k];
                    k ++;
                }
                if (sum > max) {
                    int [] temp = new int[j - i +1];
                    int index = 0;
                    k = i;
                    while (k <= j) {
                        temp[index] = array[k];
                        index ++;
                        k++;
                    }
                    result = temp;
                    max = sum;
                }
            }

        }
        return result;

     }


    public static void main(String[] args) {


        int [] arr = {1,-8,3,-9,2};

        System.out.println(Arrays.toString(maxSumArray(arr)));

    }

}
