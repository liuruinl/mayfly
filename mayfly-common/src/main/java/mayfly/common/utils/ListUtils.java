package mayfly.common.utils;

import java.util.*;

/**
 * @author: meilin.huang
 * @date: 2018/7/9 下午1:55
 */
public class ListUtils {

    /**
     * 根据比较器比较两个list中新增的对象以及删除的对象和没有改变的对象
     * @param newList  新list
     * @param oldList  旧list
     * @param comparator
     * @param <T>
     * @return
     */
    public static<T> CompareResult<T> compare(List<T> newList, List<T> oldList, Comparator<T> comparator) {
        List<T> unmodifiedValue = new ArrayList<>();

        Iterator<T> newIte = newList.iterator();
        while (newIte.hasNext()) {
            T newObj = newIte.next();
            //遍历旧数组
            Iterator<T> oldIte = oldList.iterator();
            while (oldIte.hasNext()) {
                //如果新旧数组中的对象相同，则为没有改变的对象
                T oldObj = oldIte.next();
                if (comparator.compare(newObj, oldObj) == 0) {
                    unmodifiedValue.add(oldObj);
                    oldIte.remove();
                    newIte.remove();
                }
            }
        }

        return new CompareResult<T>(newList, oldList, unmodifiedValue);
    }


    /**
     * 列表比较结果对象
     * @param <T>
     */
    public static class CompareResult<T>{
        /**
         * 新增的对象列表
         */
        private List<T> addValue;
        /**
         * 删除的对象列表
         */
        private List<T> delValue;
        /**
         * 没有改变的对象列表
         */
        private List<T> unmodifiedValue;

        public CompareResult(List<T> addValue, List<T> delValue, List<T> unmodifiedValue) {
            this.addValue = addValue;
            this.delValue = delValue;
            this.unmodifiedValue = unmodifiedValue;
        }

        public List<T> getDelValue() {
            return delValue;
        }

        public List<T> getAddValue() {
            return addValue;
        }

        public List<T> getUnmodifiedValue() {
            return unmodifiedValue;
        }
    }
}