package arrol.com.xiaomi.presenter;


import android.os.Handler;

import arrol.com.xiaomi.bean.User;
import arrol.com.xiaomi.biz.loginBiz.OnLoginListener;
import arrol.com.xiaomi.biz.loginBiz.UserBiz;
import arrol.com.xiaomi.view.IUserLoginView;

/**
 * Created by User on 2016/3/26.
 */
public class UserLoginPresenter {

    private IUserLoginView loginView;
    private UserBiz userBiz;
    private Handler mHandler=new Handler();

    public UserLoginPresenter(IUserLoginView loginView){

        this.loginView=loginView;
        this.userBiz=new UserBiz();
    }

    public void login(){
        loginView.showLoading();
        userBiz.login(loginView.getUserName(), loginView.getPassword(), new OnLoginListener() {
            @Override
            public void loginSuccess(final User user) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        loginView.toMainActivity(user);
                        loginView.hideLoading();
                    }
                });
            }

            @Override
            public void loginFailed() {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        loginView.hideLoading();
                        loginView.showFailedError();
                    }
                });
            }
        });
    }

    public void clear(){
        loginView.toRegisterActivity();
    }

}
