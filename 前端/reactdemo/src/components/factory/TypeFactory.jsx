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
            {name: "特价菜品"},
            {name: "热销菜品"  },
            {name: "饮料" },
            {name: "荤菜" },
            {name: "素菜" },
            {name: "汤类" },
            {name: "面食" },

        ];
       let factory =foodList.map(function (item,index) {

           return  <TabPane tab={item.name} key={index}> <FoodFactory /> </TabPane>
       })
        return(
            <Tabs tabPosition="left" className="foodTab" size="small ">
            {factory}
            </Tabs>
        )
    }
}
export default TypeFactory;
