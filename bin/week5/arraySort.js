    for (var i = 0; i < 10; ++i) {
      console.log(i) ; 
    }
		
		enemys[enemys.length] = {enemy: enemy, dist: dist};
		
		var enemys = [
			{enemy: 'enemy1', dist: 100},
			{enemy: 'enemy2', dist: 50},
			{enemy: 'enemy3', dist: 30},
			{enemy: 'enemy4', dist: 70},
			{enemy: 'enemy5', dist: 50},
		];
		
		enemys.sort(function(a, b){return a-b});
		/* sort by object property 
		similar to pattern matching*/
		enemys.sort(function(a, b){return a.dist-b.dist});//works as expected