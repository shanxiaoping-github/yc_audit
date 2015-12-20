//
//  ProductCell.h
//  yc_audit
//
//  Created by 单小萍 on 15/12/20.
//  Copyright © 2015年 单小萍. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface ProductCell : UITableViewCell

@property (weak, nonatomic) IBOutlet UILabel *productId;
@property (weak, nonatomic) IBOutlet UILabel *unit;
@property (weak, nonatomic) IBOutlet UILabel *totalNumber;
@property (weak, nonatomic) IBOutlet UILabel *totalPrice;
@end
