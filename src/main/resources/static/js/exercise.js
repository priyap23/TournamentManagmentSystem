angular.module('exercise', []);

angular.module('exercise')
    .run(function($rootScope, $http) {
    	$rootScope.regex = "/^-?[0-9][^\.]*$/";
        $rootScope.getTournaments = function() {
            $http.get('/getAllTournaments', {
                params: {
                   // operation: 'getTournaments'
                }
            }).then(function(result) {
                $rootScope.tournaments = result.data;
                angular.forEach($rootScope.tournaments, function(tournament) {
                    $rootScope.getPlayersInTournament(tournament.tournament_id);
                });
            });
        };

        $rootScope.addTournament = function(tournamentName, rewardAmount) {
        	var data = {
        			tournamentName: tournamentName,
        			rewardAmount: rewardAmount
       			};
        	$http.post('/addTournament', data).then(function(result) {
                $rootScope.rewardAmount = undefined;
                $rootScope.tournamentName = undefined;
                $rootScope.getTournaments();
            });
        };

        $rootScope.editTournament = function(tournament) {
            $rootScope.editTournamentId = tournament.tournament_id;
            $rootScope.editRewardAmount = tournament.reward_amount;
        };

        $rootScope.updateTournament = function(tournamentId, rewardAmount) {
            $http.put('/updateTournament', {}, {
                params:{
                    //operation: 'updateTournament',
                    tournament_id: tournamentId,
                    reward_amount: rewardAmount
                }
            }).then(function(result) {
                $rootScope.editTournamentId = undefined;
                $rootScope.editRewardAmount = undefined;
                $rootScope.getTournaments();
            });
        };

        $rootScope.removeTournament = function(tournamentId) {
            $http.delete('/removeTournament', {
                params: {
                   // operation: 'removeTournament',
                    tournament_id: tournamentId
                }
            }).then(function(result) {
                $rootScope.getTournaments();
            });
        };

        $rootScope.addPlayerIntoTournament = function(tournamentId, playerName) {
           
        	var data = {
        			 tournament_id: tournamentId,
        			 playerName: playerName
        			};
        	
        	$http.post('/addPlayerIntoTournament', data).then(function(result) {
                $rootScope.playerTournamentId = undefined;
                $rootScope.playerName = undefined;
                $rootScope.getPlayersInTournament(tournamentId);
            });
        };

        $rootScope.removePlayerFromTournament = function(tournamentId, playerId) {
            $http.delete('/removePlayerFromTournament', {
                params: {
                   // operation: 'removePlayerFromTournament',
                    tournament_id: tournamentId,
                    player_id: playerId
                }
            }).then(function(result) {
                $rootScope.getPlayersInTournament(tournamentId);
            });
        };

        $rootScope.getPlayersInTournament = function(tournamentId) {
            $http.get('/getPlayersOfTournament', {
                params: {
                  //  operation: 'getPlayersOfTournament',
                    tournament_id: tournamentId
                }
            }).then(function(result) {
                var index = $rootScope.tournaments.findIndex(function(element) {
                    return element.tournament_id == tournamentId;
                });
                $rootScope.tournaments[index].players = result.data;
            });
        };

        $rootScope.getTournaments();
    });

angular.module('exercise')
.directive('number', function () {
    return {
        require: 'ngModel',
        restrict: 'A',
        link: function (scope, element, attrs, ctrl) {
            ctrl.$parsers.push(function (input) {
                if (input == undefined) return ''
                var inputNumber = input.toString().replace(/[^0-9]/g, '');
                if (inputNumber != input) {
                    ctrl.$setViewValue(inputNumber);
                    ctrl.$render();
                }
                return inputNumber;
            });
        }
    };
});
