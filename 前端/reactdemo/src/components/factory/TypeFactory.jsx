/**
 * Created by Knove on 2017/11/13.
 *  描述：制造菜品类型的工厂
 */
import React from 'react';
import { Card, Tabs, Select  } from 'antd';
import FoodFactory from "./FoodFactory";

const TabPane = Tabs.TabPane;
const Option = Select.Option;
class TypeFactory extends React.Component{
    render(){
        const foodList=[
            {name: "特价菜品",id:1},
            {name: "热销菜品" ,id:2 },
            {name: "饮料" ,id:3},
            {name: "荤菜",id:4 },
            {name: "素菜" ,id:5},
            {name: "汤类" ,id:6},
            {name: "面食",id:7 },

        ];
       let factory =foodList.map(function (item,index) {

           return  <TabPane tab={item.name} key={index}> <FoodFactory fid={item.name} /> </TabPane>
       })
        return(
            <Tabs tabPosition="left" className="foodTab" size="small ">
            {factory}
            </Tabs>
        )
    }
}
export default TypeFactory;
