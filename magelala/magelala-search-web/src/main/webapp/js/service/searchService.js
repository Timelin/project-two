app.service("searchService",function ($http) {

    this.search=function (searchMap) {
        alert("你好2")
        return $http.post("itemSearch/search.do",searchMap);
    }
});