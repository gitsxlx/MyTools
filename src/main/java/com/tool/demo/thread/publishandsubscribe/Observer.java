package com.tool.demo.thread.publishandsubscribe;

/**
 * 观察者接口
 *
 * @author WangChao
 * @date 2020/04/15
 */
public interface Observer {

    /**
     * 当主题状态改变时,更新通知
     * @param version
     */
    public void update(int version);
	
}
