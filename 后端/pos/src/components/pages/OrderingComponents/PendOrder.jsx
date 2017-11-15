/**
 * Created by Knove on 2017/11/13.
 *  描述：服务员订餐页面的待处理订单选项卡
 */
import React from 'react';
import { Card,Input  } from 'antd';
import DeskFactory from '../../factory/DeskFactory';
class PendOrder extends React.Component{
    doSearch(value){
    console.log(value);
    }

    render(){
        const Search = Input.Search;
        let ScreenHeight=document.body.clientHeight-104; //获取 全屏幕减去title的高度


        return(
            <div>
                <Card title="待处理订单"  bodyStyle={{ width: '100%',height:ScreenHeight }}>
                    <Search
                        ref="search"
                        placeholder="输入桌号"
                        style={{ width: '100%' }}
                        onSearch={value=>this.doSearch(value)}
                    />
                      <DeskFactory />

                </Card>
            </div>
        )
    }
}
export default PendOrder;
