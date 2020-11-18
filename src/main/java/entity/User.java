package entity;

import java.util.Date;

/**
 * @author huaqiliang
 * @date: 2020/11/1723:38
 * @Description:用户类
 * @Version:
 */

public class User {
    private String userName;

    private String realName;

    private String sex;

    private Date createTime;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
