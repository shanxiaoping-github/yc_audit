//
//  ContractListViewController.m
//  yc_audit
//
//  Created by 单小萍 on 15/12/19.
//  Copyright © 2015年 单小萍. All rights reserved.
//

#import "ContractListViewController.h"
#import "appliacation_attribute.h"
#import "Colors.h"
#import "HttpGetContractsRequest.h"
#import "RuntimeContext.h"
#import "UserInfo.h"
#import "MBProgressHUDManager.h"
#import "HttpResponse.h"
#import "ContractIntroduceCell.h"
#import "ContractIntroduction.h"
#import "ContractDetailViewController.h"
#import "MJRefresh.h"
#import "LoginViewController.h"
#import "AppDelegate.h"
@interface ContractListViewController()<UITableViewDataSource,UITableViewDelegate>{
    /*合同数据*/
    NSMutableArray* contractDatas;
    /*合同选项*/
    UISegmentedControl* contractTypeSegmentedControl;
    /*合同列表*/
    __weak IBOutlet UITableView *contractTableView;
    NSUInteger currentPage;
    /*合同为空*/
    __weak IBOutlet UILabel *emptyContract;
    /*加载条*/
    MBProgressHUD* loadDialog;
    
}
@end
@implementation ContractListViewController

-(void)viewDidLoad{
    [super viewDidLoad];
    [self initData];
    [self initUI];
    currentPage = 1;
    [self getContracts:0 page:[NSString stringWithFormat:@"%li",currentPage] isfresh:YES isLoading:YES];
}

/*
 初始化数据
 */
-(void)initData{
    if (!contractDatas) {
        contractDatas = [NSMutableArray new];
    }
    currentPage = 1;
}

/*
 初始化ui
 */
-(void)initUI{
    //初始合同选择tab
    NSArray* contractTypes = @[@"待审查",@"已审查"];
    contractTypeSegmentedControl = [[UISegmentedControl alloc]initWithItems:contractTypes];
    contractTypeSegmentedControl.frame = CGRectMake(0.f, 0.f,screenWidth/3,30.f);
    contractTypeSegmentedControl.center = CGPointMake(screenWidth/2,(statusBarNomalHeight+navigationBarHorizontalHeight)/2);
    [contractTypeSegmentedControl setTintColor:[UIColor whiteColor]];
    contractTypeSegmentedControl.selectedSegmentIndex = 0;
    [contractTypeSegmentedControl addTarget:self action:@selector(selectContractType:) forControlEvents:UIControlEventValueChanged];
    self.navigationItem.titleView = contractTypeSegmentedControl;
    
    self.navigationItem.rightBarButtonItem = [[UIBarButtonItem alloc]initWithTitle:@"注销" style:UIBarButtonItemStyleDone target:self action:@selector(logout)];
    //注册合同cell
    UINib* contractNib = [UINib nibWithNibName:@"ContractIntroduceCell" bundle:nil];
    [contractTableView registerNib:contractNib forCellReuseIdentifier:@"contractCell"];
    contractTableView.estimatedRowHeight = 44.f;
    [self initPullRefresh];
}
/*!
 @author 单小萍
 
 @brief 注销
 */
-(void)logout{
    LoginViewController* loginViewController = [LoginViewController new];
    [[AppDelegate shareInstance]jumpUIViewController:loginViewController];
}
/*!
 @author shanxiaoping
 
 @brief 初始化pullRefresh
 */
-(void)initPullRefresh{
    
    [contractTableView addHeaderWithTarget:self action:@selector(headerRereshing)];
    contractTableView.headerRefreshingText = @"正在刷新中";
    
    [contractTableView addFooterWithTarget:self action:@selector(footerRereshing)];
    contractTableView.footerRefreshingText = @"正在拉取数据";
}
/*!
 @author shanxiaoping
 
 @brief 刷新
 */
-(void)headerRereshing{
    currentPage = 1;
    [self getContracts:contractTypeSegmentedControl.selectedSegmentIndex page:[NSString stringWithFormat:@"%li",currentPage] isfresh:YES isLoading:NO];
}
/*!
 @author shanxiaoping
 
 @brief 下拉
 */
-(void)footerRereshing{
    NSUInteger nextPage = currentPage+1;
    [self getContracts:contractTypeSegmentedControl.selectedSegmentIndex page:[NSString stringWithFormat:@"%li",nextPage] isfresh:NO isLoading:NO];
}
/*
 选取合同
 */
-(void)selectContractType:(id)sender{
    currentPage = 1;
    UISegmentedControl* control = sender;
    [self getContracts:control.selectedSegmentIndex page:[NSString stringWithFormat:@"%li",currentPage] isfresh:YES isLoading:YES];
}
/*
 * auditStatus 0待审核 1已审核
 */
-(void)getContracts:(NSInteger)auditStatus page:(NSString*)page isfresh:(BOOL)isfresh isLoading:(BOOL)isLoading{
    NSString* action;
    switch (auditStatus) {
        case 0:
            action = wait_contract_action;
            break;
        case 1:
            action = already_contract_action;
            break;
    }
    HttpGetContractsRequest* getContractsRequest = [HttpGetContractsRequest new];
    HttpResponse* contractResponse = [HttpResponse new];
    contractResponse.success = ^(id httpRequest,id httpResponse){
        [contractTableView footerEndRefreshing];
        [contractTableView headerEndRefreshing];
        loadDialog.hidden = YES;
        if (isfresh) {
            if (getContractsRequest.contracts.count > 0) {
                [contractDatas removeAllObjects];
                [contractDatas addObjectsFromArray:getContractsRequest.contracts];
                [contractTableView reloadData];
                if (isLoading) {
                    emptyContract.hidden = YES;
                }
            }else{
                if (isLoading) {
                    emptyContract.text = @"暂无合同";
                    emptyContract.hidden = NO;
                }
            }
        }else{
            if (getContractsRequest.contracts.count > 0) {
                currentPage++;
                [contractDatas addObjectsFromArray:getContractsRequest.contracts];
                [contractTableView reloadData];
            }else{
                [self showMessage:@"没有更多数据"];
            }
            
        }
        
    };
    contractResponse.faile = ^(id httpRequest,NSError* error){
        [contractTableView footerEndRefreshing];
        [contractTableView headerEndRefreshing];
        loadDialog.hidden = YES;
    };
    getContractsRequest.httpResponse = contractResponse;
    getContractsRequest.auditStatus = auditStatus;
    UserInfo* userInfo = [[RuntimeContext shareInstance] getClasstData:[UserInfo class]];
    [getContractsRequest setPramaValues:@[action,userInfo.userName,page]];
    if (isLoading) {
        emptyContract.text = @"";
        emptyContract.hidden = NO;
        loadDialog = [MBProgressHUDManager showLoad:@"获取合同中" view:self.view];
    }
    [getContractsRequest submitRequest];
}
-(UIColor *)navigationColor{
    return kColorWithRGB(74,202,226,1);
}

#pragma mark tableView  代理
-(NSInteger)numberOfSectionsInTableView:(UITableView *)tableView{
    return 1;
}

-(NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section{
    return [contractDatas count];
}

-(UITableViewCell*)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath{
    ContractIntroduceCell* contractIntroduceCell = [contractTableView dequeueReusableCellWithIdentifier:@"contractCell"];
    contractIntroduceCell.selectionStyle = UITableViewCellSelectionStyleBlue;
    ContractIntroduction* contractIntroduction = contractDatas[indexPath.row];
    contractIntroduceCell.contractId.text = [NSString stringWithFormat:@"%@%@",@"合同编号：",contractIntroduction.contractId];
    contractIntroduceCell.supplierName.text = [NSString stringWithFormat:@"%@%@",@"供应商名称：",contractIntroduction.supplierName];
    contractIntroduceCell.oprerationName.text =  [NSString stringWithFormat:@"%@%@",@"送审人：",contractIntroduction.operater];
    contractIntroduceCell.auditStatus.text = [NSString stringWithFormat:@"%@%@",@"审核状态：",[contractIntroduction.status isEqualToString:@"0"]?@"待审核":[contractIntroduction.status isEqualToString:@"1"]?@"已通过":@"未通过"];
    
    return contractIntroduceCell;
}
-(void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath{
    ContractIntroduction* contractIntroduction = contractDatas[indexPath.row];
    ContractDetailViewController* contractDetailViewController = [ContractDetailViewController new];
    contractDetailViewController.contractIntroduction = contractIntroduction;
    [self.navigationController pushViewController:contractDetailViewController animated:YES];
}
-(UIStatusBarStyle)preferredStatusBarStyle{
    return UIStatusBarStyleLightContent;
}
@end
