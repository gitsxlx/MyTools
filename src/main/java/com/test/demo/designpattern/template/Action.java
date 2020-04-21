package com.test.demo.designpattern.template;

/**
 * Action
 *
 * @author WangChao
 * @date 2020/04/20
 */
public abstract class Action {

    public static final int EAT = 1;
    public static final int SLEEP = 5;
    public static final int WORK = 10;

    public void command(int code) {
        switch (code) {
            case EAT:
                this.eat();
                break;
            case SLEEP:
                this.sleep();
                break;
            case WORK:
                this.work();
                break;
            case EAT + SLEEP + WORK:
                this.eat();
                this.sleep();
                this.work();
                break;
            default:
                break;
        }
    }

    /**
     * 吃
	 */
	public abstract void eat();

	/**
	 * 睡觉
	 */
    public abstract void sleep();

	/**
	 * 工作
	 */
    public abstract void work();

}
