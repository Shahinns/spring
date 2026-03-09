var cartApp = angular.module('cartApp', []);
cartApp.controller('cartCtrl', function ($scope, $http) {

    $scope.refreshCart = function(cartId) {
        $http.get('/webstore/rest/cart/' + cartId)
            .then(function(response) {
                $scope.cart = response.data;
            });
    };

    $scope.clearCart = function() {
        $http.delete('/webstore/rest/cart/' + $scope.cartId)
            .then(function() {
                $scope.refreshCart($scope.cartId);
            });
    };

    $scope.initCartId = function(cartId) {
        $scope.cartId = cartId;
        $scope.refreshCart($scope.cartId);
    };

    $scope.addToCart = function(productId) {
        $http.put('/webstore/rest/cart/add/' + productId)
            .then(function() {
                alert("Product Successfully added to the Cart!");
            });
    };

    $scope.removeFromCart = function(productId) {
        $http.put('/webstore/rest/cart/remove/' + productId)
            .then(function() {
                $scope.refreshCart($scope.cartId);
            });
    };
});