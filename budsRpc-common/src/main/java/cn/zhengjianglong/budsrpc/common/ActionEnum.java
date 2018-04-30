package cn.zhengjianglong.budsrpc.common;

/**
 * @author: zhengjianglong
 * @create: 2018-04-30 23:31
 */
public enum ActionEnum {
    // 注册
    ACTION_REGISTRY("REGISTRY"),
    // 订阅
    ACTION_SUBSCRIBE("SUBSCRIBE");

    ActionEnum(String action) {
        this.action = action;
    }

    private String action;

    public static ActionEnum getAction(String action) {
        for (ActionEnum actionEnum: values()) {
            if (actionEnum.action.equals(action)) {
                return actionEnum;
            }
        }
        return null;
    }
}
