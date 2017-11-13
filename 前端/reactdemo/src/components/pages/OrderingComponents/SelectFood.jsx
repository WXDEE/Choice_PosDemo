/**
 * Created by Knove on 2017/11/13.
 *  描述：服务员订餐页面的
 */
import React from 'react';
import { Card,Input, Tabs, Select } from 'antd';
import TypeFactory from "../../factory/TypeFactory";

const TabPane = Tabs.TabPane;
const Option = Select.Option;
function changeTabPosition (tabPosition) {
    this.setState({ tabPosition });
}
class SelectFood extends React.Component{

    render(){
        let ScreenHeight=document.body.clientHeight-162; //获取 全屏幕减去title的高度
        const Search = Input.Search;
        return(
                <Card title="选餐区"  bodyStyle={{ width: '100%',height:0.75*ScreenHeight }}>
                    <Search
                        ref="search"
                        placeholder="请输菜品汉拼首字母"
                        style={{ width: '100%' }}
                        onSearch={value=>this.doSearch(value)}
                    />


                      <TypeFactory />

                </Card>
        )
    }
}
export default SelectFood;
