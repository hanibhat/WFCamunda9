/**
 * Created by hbhat on 26-Jun-17.
 */
var myMod = angular.module('myApp', ['ngMessages'])
    .controller('myCont', function ($scope, $http) {
        var orderForm = {
            name: "",
            org: "",
            emailAdd: "",
            designSpec: "",
            otime: new Date().toString()
        };
        $scope.orderForm = orderForm;

        $scope.submit = function (valid) {
            if (valid) {
                // console.log($scope.form);
                $http.post('/order', $scope.orderForm)
                    .then(function (data, status, headers, config) {
                        if (data.data == 'invalidEmail') {
                            var $toastContent = $('<span style="color: red">The email address you provided could not be validated</span>');
                            Materialize.toast($toastContent, 5000);
                        } else {
                            var $toastContent = $('<span>Your order has been submitted</span>');
                            Materialize.toast($toastContent, 5000);
                            $('.modal').modal('close');
                            $scope.orderForm = {};
                            $scope.myForm.$setPristine();
                            $scope.myForm.$setUntouched();
                        }
                    }).catch(function (data, status, headers, config) {
                    // console.log('Something is wrong!', data);
                    var $toastContent = $('<span style="color: red">Your order could not be submitted! Please try again later.</span>');
                    Materialize.toast($toastContent, 5000);
                });
            }
            else {

            }
        };
    });

$(document).ready(function() {
    $('.datepicker').pickadate({
        selectMonths: true, // Creates a dropdown to control month
        selectYears: 15 // Creates a dropdown of 15 years to control year
    });
});