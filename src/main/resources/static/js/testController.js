app.controller('testControler', function ($scope, $http, $window) {

    $scope.sortBy = 'id';
    $scope.sortReverse = false;
    $scope.currentPage = 0;
    $scope.pageSize = 5;
    $scope.selectedTest = null;
    $scope.error = false;
    $scope.containsData = false;

    var testUrl = "http://localhost:8080/tests"

    var getTests = function () {
        $http.get(testUrl)
            .then(function (response) {
                if (response.data.length > 0) {
                    $scope.tests = response.data;
                    $scope.containsData = true;
                    $scope.totalPages = Math.ceil(response.data.length / $scope.pageSize);
                } else {
                    $scope.defaultMessage = "No tests found";
                    $scope.containsData = false;
                }
            }, function (response) {
                $scope.errorMessage = "Something went wrong: " + response.data;
                $scope.containsData = false;
                $scope.error = true;
            })
    }

    getTests();

    $scope.sortFunction = function (sortByValue) {
        if ($scope.sortBy == sortByValue) {
            $scope.sortReverse = !$scope.sortReverse;
        } else {
            $scope.sortReverse = false;
        }
    }

    $scope.selectPage = function (index) {
        $scope.currentPage = index;
    }


    $scope.setPage = function (page) {
        if (page < 0 || page > $scope.totalPages) {
            return;
        }
        $scope.currentPage = page;
    }

    $scope.pageRange = function (arrayLength) {
        $scope.totalPages = Math.ceil(arrayLength / $scope.pageSize);
        var rangeToReturn = [];
        for (var i = 0; i < $scope.totalPages; i++) {
            rangeToReturn.push(i);
        }
        return rangeToReturn;
    }

    // $scope.isSelected = function (test) {
    // }

    $scope.addTest = function () {
        if ($scope.newName != null && $scope.newName.length > 0) {

            var xmlMessage = createXmlBody($scope.newName);

            $http.post('http://localhost:8080/test',
                    xmlMessage,
                {
                    headers: {
                        'Content-Type': 'application/xml'
                    }
                })
                .then(function (response) {
                    $window.alert(response.data.message);
                    getTests();
                }, function (response) {
                    $window.alert(response.message);
                    getTests()
                })
        }
    }

    var createXmlBody = function (nameValue) {
        return element('Test', element("name", nameValue));
    }

    var element = function(name,content){
        var xml
        if (!content){
            return;
        }
        else {
            xml='<'+ name + '>' + content + '</' + name + '>'
        }
        return xml
    }

    $scope.delete = function(id) {
        $http.delete('http://localhost:8080/delete/' + id)
            .then(function (response) {
                $window.alert(response.data.message);
                getTests();
            }, function (response) {
                $window.alert(response.message);
                getTests()
            })
        }
});