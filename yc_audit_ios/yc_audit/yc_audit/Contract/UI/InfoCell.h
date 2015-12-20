//
//  InfoCell.h
//  yc_audit
//
//  Created by 单小萍 on 15/12/20.
//  Copyright © 2015年 单小萍. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface InfoCell : UITableViewCell
/*合同编号*/
@property (weak, nonatomic) IBOutlet UILabel *contractId;
/*公司名称*/
@property (weak, nonatomic) IBOutlet UILabel *company;
/*付款协议*/
@property (weak, nonatomic) IBOutlet UILabel *payAgreement;
/*签订日期*/
@property (weak, nonatomic) IBOutlet UILabel *signedData;
/*发送日期*/
@property (weak, nonatomic) IBOutlet UILabel *sendData;
/*接受日期*/
@property (weak, nonatomic) IBOutlet UILabel *acceptData;
/*开票名称*/
@property (weak, nonatomic) IBOutlet UILabel *openTicketName;
/*供货商名称*/
@property (weak, nonatomic) IBOutlet UILabel *supplyName;

@end
