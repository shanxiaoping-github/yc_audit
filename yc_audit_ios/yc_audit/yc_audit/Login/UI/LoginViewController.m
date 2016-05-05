//
//  LoginViewController.m
//  yc_audit
//
//  Created by 单小萍 on 15/12/19.
//  Copyright © 2015年 单小萍. All rights reserved.
//

#import "LoginViewController.h"
#import "Colors.h"
#import "HttpLoginRequest.h"
#import "HttpResponse.h"
#import "ContractListViewController.h"
#import "MBProgressHUDManager.h"
#import "AppDelegate.h"
#import "RuntimeContext.h"
@interface LoginViewController ()<UITextFieldDelegate>{
    //用户名
    __weak IBOutlet UITextField *userName;
    //密码
    __weak IBOutlet UITextField *passWord;
    //登录按钮
    __weak IBOutlet UIButton *loginBtn;
    //加载条
    MBProgressHUD* loginLoadDialog;
}
@end
@implementation LoginViewController
- (void)viewDidLoad {
    [super viewDidLoad];
    [self initUI];
    // Do any additional setup after loading the view from its nib.
}
/*
 初始化ui
 */
-(void)initUI{
    loginBtn.layer.cornerRadius = loginBtn.frame.size.height/2;
   
}
#pragma mark 点击响应
/*
  登录
 */
- (IBAction)loginAction:(id)sender {
    NSString* userNameStr = userName.text;
    NSString* pwdStr = passWord.text;
    HttpLoginRequest* httpLoginRequest = [HttpLoginRequest new];
    HttpResponse* loginResponse = [HttpResponse new];
    loginResponse.success = ^(id httpRequest,id response){
        loginLoadDialog.hidden = YES;
        if ([httpLoginRequest.status isEqualToString:@"0"]) {
            [[RuntimeContext shareInstance]putClassData:httpLoginRequest.userInfo forkey:[UserInfo class]];
            UINavigationController* contractListNavigationController =  [[UINavigationController alloc]initWithRootViewController:[ContractListViewController new]];
            [[AppDelegate shareInstance]jumpUIViewController:contractListNavigationController];
        }else{
            [MBProgressHUDManager showMessage:@"登录失败，用户名或密码错误" view:self.view];
        }
    };
    loginResponse.faile = ^(id httpRequest,NSError* error){
        loginLoadDialog.hidden = YES;
        [MBProgressHUDManager showMessage:@"登录异常" view:self.view];
    };
    httpLoginRequest.httpResponse = loginResponse;
    [httpLoginRequest setPramaValues:@[login_action,userNameStr,pwdStr]];
    loginLoadDialog = [MBProgressHUDManager showLoad:@"登录中" view:self.view];
    [httpLoginRequest submitRequest];
    
}
#pragma mark textFielDelegate
-(BOOL)textFieldShouldReturn:(UITextField *)textField{
    [textField resignFirstResponder];
    return YES;
}
-(BOOL)textField:(UITextField *)textField shouldChangeCharactersInRange:(NSRange)range replacementString:(NSString *)string{
    NSInteger textLeng = textField.text.length-range.length+string.length;
    if (textLeng > 0) {
        if (textField == userName&&passWord.text.length > 0) {
            loginBtn.enabled = YES;
            loginBtn.backgroundColor = lightBlueColor;
        }
        if(textField == passWord&&userName.text.length > 0){
            loginBtn.enabled = YES;
            loginBtn.backgroundColor = lightBlueColor;
        }
    }else{
        loginBtn.enabled = NO;
        loginBtn.backgroundColor = lightGrayColor;
    }
    if (textLeng > 11) {
        return NO;
    }
    return YES;
}

@end
