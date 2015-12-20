//
//  ContractDetailViewController.m
//  yc_audit
//
//  Created by 单小萍 on 15/12/20.
//  Copyright © 2015年 单小萍. All rights reserved.
//
#import "ContractDetailViewController.h"
#import "HttpGetContractDetailRequest.h"
#import "HttpResponse.h"
#import "MBProgressHUDManager.h"
#import "ContractSection.h"
#import "ContractDetail.h"
#import "InfoCell.h"
#import "TermsCell.h"
#import "ProductCell.h"
#import "AuditRecordCell.h"
#import "AuditOperationCell.h"
#import "Colors.h"
#import "appliacation_attribute.h"
#import "Fonts.h"
#import "ContractProducts.h"
#import "CheckInfo.h"
#import "MathUtil.h"
#import "StringUtil.h"
#import "HttpSubmitAuditOperationRequest.h"
#import "RuntimeContext.h"
#import "UserInfo.h"

@interface ContractDetailViewController ()<UITableViewDataSource,UITableViewDelegate>{
    /*合同详情tableView*/
    __weak IBOutlet UITableView *detailTableView;
    /*获取详情失败*/
    __weak IBOutlet UILabel *detailFailLabel;
    /*合同选项*/
    NSArray* contractSections;
    /*合同详情*/
    ContractDetail* contractDetail;
    /*审核*/
    UISwitch* isAgreenSwitch;
    UILabel* isAgreenLable;
    UITextView* auditContent;
}
@end
@implementation ContractDetailViewController
@synthesize contractIntroduction = _contractIntroduction;
- (void)viewDidLoad {
    [super viewDidLoad];
    //初始化数据
    [self initData];
    //初始化ui
    [self initUI];
    //获取合同详情
    [self getContractDetail];
    // Do any additional setup after loading the view from its nib.
}
/*初始化数据*/
-(void)initData{
    contractSections = [NSArray new];
}
/*初始化UI*/
-(void)initUI{
    self.navigationItem.title = @"合同详情";
    detailTableView.estimatedRowHeight = 80.f;
    //注册cell
    UINib* nib0 = [UINib nibWithNibName:contract_cell_info bundle:nil];
    [detailTableView registerNib:nib0 forCellReuseIdentifier:contract_cell_info];
    
    UINib* nib1 = [UINib nibWithNibName:contract_cell_terms bundle:nil];
    [detailTableView registerNib:nib1 forCellReuseIdentifier:contract_cell_terms];
    
    UINib* nib2 = [UINib nibWithNibName:contract_cell_product bundle:nil];
    [detailTableView registerNib:nib2 forCellReuseIdentifier:contract_cell_product];
    
    UINib* nib3 = [UINib nibWithNibName:contract_cell_audit_record bundle:nil];
    [detailTableView registerNib:nib3 forCellReuseIdentifier:contract_cell_audit_record];
    
    UINib* nib4 = [UINib nibWithNibName:contract_cell_audit_operation bundle:nil];
    [detailTableView registerNib:nib4 forCellReuseIdentifier:contract_cell_audit_operation];
    
}

/*
 *获取合同详情
 */
-(void)getContractDetail{
    HttpGetContractDetailRequest* getContractDetailRequest = [HttpGetContractDetailRequest new];
    HttpResponse* detailResponse = [HttpResponse new];
    detailResponse.success = ^(id httpRequest,id response){
        [self stopIndicatorView];
        if (getContractDetailRequest.contractDetail) {
            detailFailLabel.hidden = YES;
            contractDetail = getContractDetailRequest.contractDetail;
            contractSections = [ContractSection getContracSections];
            [detailTableView reloadData];
        }else{
            detailFailLabel.hidden = NO;
            detailFailLabel.text = @"获取合同详情失败";
        }
    };
    detailResponse.faile = ^(id httpRequest,NSError* error){
        [self stopIndicatorView];
        detailFailLabel.hidden = NO;
        detailFailLabel.text = @"获取合同详情失败";
    };
    getContractDetailRequest.httpResponse = detailResponse;
    [getContractDetailRequest setPramaValues:@[detail_action,_contractIntroduction.contractId,_contractIntroduction.processId,_contractIntroduction.processNode]];
    [self showIndicatorView];
    [getContractDetailRequest submitRequest];
}
#pragma mark tableView代理
-(NSInteger)numberOfSectionsInTableView:(UITableView *)tableView{
    return [contractSections count];
}
-(NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section{
    switch (section) {
        case 0:
        case 1:
        case 4:
            return 1;
        case 2:
            return contractDetail.products.count;
        case 3:
            return contractDetail.checkInfos.count;
        default:
            return 0;
    }
}
-(CGFloat)tableView:(UITableView *)tableView heightForHeaderInSection:(NSInteger)section{
    return 40.f;
}
-(CGFloat)tableView:(UITableView *)tableView heightForFooterInSection:(NSInteger)section{
    return 0.1f;
}
-(UIView*)tableView:(UITableView *)tableView viewForHeaderInSection:(NSInteger)section{
    UILabel* sectionLabel = [UILabel new];
    sectionLabel.frame = CGRectMake(0.f,0.f,screenWidth,40.f);
    sectionLabel.backgroundColor = baseColor;
    sectionLabel.font = title_font;
    sectionLabel.textColor = lightBlueColor;
    ContractSection* contractSection = contractSections[section];
    sectionLabel.text = [NSString stringWithFormat:@"%@%@",@"  ",contractSection.sectionDesc];
    return sectionLabel;
}
-(UITableViewCell*)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath{
    return [self getContractDetailCell:indexPath tableView:tableView];
}
/*
 *返回cell
 *@parame indexPath行列信息
 */
-(UITableViewCell*)getContractDetailCell:(NSIndexPath*)indexPath tableView:(UITableView*)tableView{
    ContractSection* contractSection = contractSections[indexPath.section];
    UITableViewCell* contractCell = [tableView dequeueReusableCellWithIdentifier:contractSection.cellNibName];
    [self filterContractCell:contractCell indexPath:indexPath tableView:tableView];
    return contractCell;
}
/*
 *过滤cell 对cell进行操作
 *@parame indexPath行列信息
 */

-(void)filterContractCell:(UITableViewCell*)contractCell indexPath:(NSIndexPath*)indexPath tableView:(UITableView*)tableView{
    switch (indexPath.section) {
        case 0:
            [self operationInfoCell:(InfoCell*)contractCell indexPath:indexPath tableView:tableView];
            break;
        case 1:
            [self operationTermsCell:(TermsCell*)contractCell indexPath:indexPath tableView:tableView];
            break;
        case 2:
            [self operationProductCell:(ProductCell*)contractCell indexPath:indexPath tableView:tableView];
            break;
        case 3:
            [self operationAuditRecordCell:(AuditRecordCell*)contractCell indexPath:indexPath tableView:tableView];
            break;
        case 4:
            [self operationAuditOperationCell:(AuditOperationCell*)contractCell indexPath:indexPath tableView:tableView];
            break;
    }
}
/*
 *操作InfoCell
 */
-(void)operationInfoCell:(InfoCell*)infoCell indexPath:(NSIndexPath*)indexPath tableView:(UITableView*)tableView{
    infoCell.contractId.text = [NSString stringWithFormat:@"%@%@",@"合同编号：",contractDetail.contractInfo[0].contractId];
    infoCell.company.text = [NSString stringWithFormat:@"%@%@",@"公司名称：",contractDetail.crateContractOrg];
    infoCell.payAgreement.text =  [NSString stringWithFormat:@"%@%@",@"收付款协议：",contractDetail.bdPayterm[term_name]];
    infoCell.signedData.text = [NSString stringWithFormat:@"%@%@",@"签订日期：",contractDetail.contractInfo[0].date1];
    infoCell.sendData.text = [NSString stringWithFormat:@"%@%@",@"发货日期：",contractDetail.contractInfo[0].startDate];
    infoCell.acceptData.text = [NSString stringWithFormat:@"%@%@",@"到货日期：",contractDetail.contractInfo[0].endDate];
    infoCell.openTicketName.text = [NSString stringWithFormat:@"%@%@",@"开票名称：",contractDetail.orgName];
    infoCell.supplyName.text = [NSString stringWithFormat:@"%@%@",@"供应商名称：",    contractDetail.contractInfo[0].supplierName];
}
/*
 *操作TermsCell
 */
-(void)operationTermsCell:(TermsCell*)termsCell indexPath:(NSIndexPath*)indexPath tableView:(UITableView*)tableView{
    termsCell.termsContent.text = contractDetail.contractInfo[0].txt7;
}
/*
 *操作ProductCell
 */
-(void)operationProductCell:(ProductCell*)productCell indexPath:(NSIndexPath*)indexPath tableView:(UITableView*)tableView{
    ContractProducts* product = contractDetail.products[indexPath.row];
    productCell.productId.text = product.productname;
    productCell.unit.text = [NSString stringWithFormat:@"(%@元/%@)",product.unitprice,product.unit];
    productCell.totalPrice.text = [NSString stringWithFormat:@"总价格：%@元",[MathUtil decimalNumberMutiplyWithString:product.unitprice value2:product.total]];
    productCell.totalNumber.text = [NSString stringWithFormat:@"总数量：%@%@",product.total,product.unit];
}


/*
 *操作AuditRecordCell
 */
-(void)operationAuditRecordCell:(AuditRecordCell*)auditRecordCell indexPath:(NSIndexPath*)indexPath tableView:(UITableView*)tableView{
    CheckInfo* checkInfo = contractDetail.checkInfos[indexPath.row];
    auditRecordCell.name.text = [NSString stringWithFormat:@"审核人姓名：%@",checkInfo.realname];
    auditRecordCell.name.text = [NSString stringWithFormat:@"审核人身份：%@",checkInfo.rolename];
    auditRecordCell.content.text = checkInfo.opinion;
}
/*
 *操作AuditOperationCell
 */
-(void)operationAuditOperationCell:(AuditOperationCell*)auditOperationCell indexPath:(NSIndexPath*)indexPath tableView:(UITableView*)tableView{
    static int operationCount = 1;
    if (operationCount <= 1) {
        isAgreenLable = auditOperationCell.operateDesc;
        auditContent = auditOperationCell.opinion;
        isAgreenSwitch = auditOperationCell.isAgreen;
        [isAgreenSwitch addTarget:self action:@selector(agreenSwitchChanged:) forControlEvents:UIControlEventValueChanged];
        [auditOperationCell.submitBtn addTarget:self action:@selector(submitAuditContent) forControlEvents:UIControlEventTouchDown];
    }
    operationCount++;
}
/*
 *同意开关操作响应
 */
-(void)agreenSwitchChanged:(id)sender{
    UISwitch* agreenSwitch = sender;
    if (agreenSwitch.on) {
        isAgreenLable.textColor = lightBlueColor;
        isAgreenLable.text = @"同意";
    }else{
        isAgreenLable.textColor = [UIColor redColor];
        isAgreenLable.text = @"不同意";
    }

}
/*
 *提交审核
 */
-(void)submitAuditContent{
    HttpSubmitAuditOperationRequest* submitAuditOperationRequest = [HttpSubmitAuditOperationRequest new];
    //返回响应
    HttpResponse* submitAuditOperationResponse = [HttpResponse new];
    submitAuditOperationResponse.success = ^(id httpRequest,id httpResponse){
        [self stopLoadDialog];
        int flag = [submitAuditOperationRequest.flag intValue];
        switch (flag) {
            case 0:
                [self showMessage:@"审核成功,进入下一级审核"];
                break;
            case 1:
                [self showMessage:@"审核结束,合同审核状态保存成功"];
                break;
            case -1:
                [self showMessage:@"审核结束,合同审核状态保存失败"];
                break;
            default:
                [self showMessage:@"审核失败"];
                break;
        }
    };
    submitAuditOperationResponse.faile = ^(id httpRequest,NSError* error){
        [self stopLoadDialog];
    };
    submitAuditOperationRequest.httpResponse = submitAuditOperationResponse;
    //提交参数
    UserInfo* userInfo = [[RuntimeContext shareInstance] getClasstData:[UserInfo class]];
    [submitAuditOperationRequest setPramaValues:@[audit_action,contractDetail.contractInfo[0].contractId,userInfo.userName,isAgreenSwitch.on?@"1":@"2",auditContent.text,_contractIntroduction.processId,_contractIntroduction.processNode]];
    
    //显示loading
    [self showLoadDialog:@"提交审核中"];
    //提交
    [submitAuditOperationRequest submitRequest];

}
-(UIStatusBarStyle)preferredStatusBarStyle{
    return UIStatusBarStyleLightContent;
}
@end
