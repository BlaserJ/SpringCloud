import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;
import java.util.*;

public class GatewayTest {
    @Test
    public void t1(){
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        System.out.println(zonedDateTime);
    }

    @Test
    public void t2(){
        List<Integer> result = new ArrayList<>(); //存放结果
        List<Integer> list = new ArrayList<>();  //存放原料
        list.add(1070);
        list.add(1350);
        list.add(1250);
        list.add(1270);
        list.add(1210);
        list.add(1110);
        list.add(1140);
        list.add(1350);
        list.add(1950);
        list.add(1260);
        list.add(1140);
        list.add(880);
        list.add(710);
        list.add(730);
        list.add(720);
        list.add(700);
        list.add(510);
        list.add(580);
        list.add(610);
        list.add(640);
        list.add(860);
        list.add(820);
        list.add(690);
        list.add(810);
        list.add(540);
        list.add(660);

        Integer target = 2310;

        list.sort(Comparator.comparingInt(i -> i)); //排序
        System.out.println("数组排序: " + list);
        if(findOfTwo(list, target, result)){
            System.out.println(result);
        }else{
            findOfThree(list, target, result);
        }
    }

    public void findOfThree(List<Integer> list, Integer target, List<Integer> result){
        System.out.println("----------两数相加失败，尝试暴力三数相加求解------------");
        int size = list.size();
        for(int i = 0; i < size; i++){
            Integer pullout = list.get(i); //从list中拉取出一个值
            System.out.println("---->尝试从list中拉取出第" + i + "位元素: " + pullout);
            List<Integer> sublist = list.subList(i + 1, size); //获取拉取后的list
            if(findOfTwo(sublist, target - pullout, result)){
                result.add(pullout);
                System.out.println(result);
                return;
            }
        }
        System.out.println("暴力尝试失败");
    }

    public boolean findOfTwo(List<Integer> list, Integer target, List<Integer> result){
        int half = target / 2;
        int middle = 0;//找到中间值

        for(int i = 0; i < list.size(); i++){
            if(list.get(i) >= half){
                middle = i;
                break;
            }
            if(i == list.size() - 1){
                return false;
            }
        }

        List<Integer> pre = list.subList(0, middle);  //小于中间值的部分
        List<Integer> post = list.subList(middle, list.size());  //大于中间值的部分

        System.out.println("pre: " + pre);
        System.out.println("post: " + post);

        for(Integer one : pre){
            System.out.println("尝试匹配" + one + "...");
            if(post.contains(target - one)){
                System.out.println("匹配成功");
                result.add(one);
                result.add(post.get(post.indexOf(target - one)));
                return true;
            }
            System.out.println("匹配失败");
        }

        return false;
    }
}
