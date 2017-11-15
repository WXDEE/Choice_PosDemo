/**
 * Created by Knove on 2017/11/9.
 */
import React from 'react';
import {  Menu, Dropdown,Icon} from 'antd';

const rightMenu = (
    <Menu>
        <Menu.Item key="0">
            <a href="#/main/UserOrdering">服务员订餐页面</a>
        </Menu.Item>
        <Menu.Item key="1">
            <a href="#/main/FoodInfo">后台运维页面-菜品信息</a>
        </Menu.Item>
        <Menu.Item key="2">
            <a href="#/main/OrderInfo">后台运维页面-订单明细</a>
        </Menu.Item>
        <Menu.Divider />
        <Menu.Item key="3">雄鹰订餐系统</Menu.Item>
    </Menu>
);
class Title extends React.Component{

    render(){
        return(
            <div >
                <div className="titleAll">
                    <img src={require("../../img/logo.png")} />
                    <span className="titleFont">欢迎使用雄鹰订餐系统</span>
                    <Dropdown overlay={rightMenu} trigger={['click']} className="icon">
                        <a className="ant-dropdown-link icon" href="#" >
                            <Icon type="ellipsis"   />
                        </a>
                    </Dropdown>

                </div>
                {this.props.children}

            </div>
        )
    }
}
export default Title;
