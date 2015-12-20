//
//  ContractIntroduceCell.h
//  yc_audit
//
//  Created by 单小萍 on 15/12/20.
//  Copyright © 2015年 单小萍. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface ContractIntroduceCell : UITableViewCell
/*合同编号*/
@property (weak, nonatomic) IBOutlet UILabel *contractId;
/*供应商名称*/
@property (weak, nonatomic) IBOutlet UILabel *supplierName;
/*送审人*/
@property (weak, nonatomic) IBOutlet UILabel *oprerationName;
/*审核状态*/
@property (weak, nonatomic) IBOutlet UILabel *auditStatus;
@end
