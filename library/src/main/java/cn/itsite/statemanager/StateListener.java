package cn.itsite.statemanager;

/**
 * Author：leguang on 2016/10/12 0009 15:49
 * Email：langmanleguang@qq.com
 * <p>
 * 页面状态点击事件监听器。
 */

public interface StateListener {
    interface ConvertListener {

        void convert(BaseViewHolder holder, StateLayout stateLayout);
    }
}