app.controller("baseController",function ($scope) {
    //初始化分页对象
    // 初始化分页参数
    $scope.paginationConf={
        currentPage:1,// 当前页号
        totalItems:10,// 总记录数
        itemsPerPage:10,// 页大小
        perPageOptions:[10,20,30,40,50],// 可选择得每页大小
        onChange:function () {
            // 当上述的参数发生变化了后触发
            $scope.reloadList();
        }
    };


    // 重新加载
    $scope.reloadList=function () {
        //  $scope.findPage($scope.paginationConf.currentPage,$scope.paginationConf.itemsPerPage);
        $scope.search($scope.paginationConf.currentPage,$scope.paginationConf.itemsPerPage);
    };

    // 复选框选择与取消
    // 定义一个放置选择了id的数组
    $scope.selectedIds=[];
    $scope.updateSelection=function ($event, id) {
        if($event.target.checked){
            //将选择的选框元素添加到数组
            $scope.selectedIds.push(id);
        }else {
            // 移除指定的数组元素
            var  index =$scope.selectedIds.indexOf(id);
            // 删除位置，删除个数
            $scope.selectedIds.splice(index,1);
        }
    };

    $scope.jsonToString = function (jsonStrList, key) {
        var str = "";
        //将json字符串转换为一个json对象
        var jsonArray = JSON.parse(jsonStrList);
        for (var i = 0; i < jsonArray.length; i++) {
            var obj = jsonArray[i];

            if(str.length > 0){
                str += "," + obj[key]
            } else {
                str = obj[key];
            }
        }

        return str;
    }

});