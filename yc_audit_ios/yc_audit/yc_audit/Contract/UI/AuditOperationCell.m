//
//  AuditOperationCell.m
//  yc_audit
//
//  Created by 单小萍 on 15/12/20.
//  Copyright © 2015年 单小萍. All rights reserved.
//

#import "AuditOperationCell.h"

@implementation AuditOperationCell

- (void)awakeFromNib {
    // Initialization code
    self.submitBtn.layer.cornerRadius = self.submitBtn.frame.size.height/2;
}

- (void)setSelected:(BOOL)selected animated:(BOOL)animated {
    [super setSelected:selected animated:animated];

    // Configure the view for the selected state
}

@end
