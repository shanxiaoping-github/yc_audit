//
//  AuditOperationCell.h
//  yc_audit
//
//  Created by 单小萍 on 15/12/20.
//  Copyright © 2015年 单小萍. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface AuditOperationCell : UITableViewCell
/*是否同意*/
@property (weak, nonatomic) IBOutlet UISwitch *isAgreen;
/*选择描述*/
@property (weak, nonatomic) IBOutlet UILabel *operateDesc;
/*意见内容*/
@property (weak, nonatomic) IBOutlet UITextView *opinion;
/*提交按钮*/
@property (weak, nonatomic) IBOutlet UIButton *submitBtn;
/*底部高度*/
@property (weak, nonatomic) IBOutlet NSLayoutConstraint *bottomLayoutConstraintHeight;
@end
