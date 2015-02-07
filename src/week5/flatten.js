"using strict";

var 
	emptyObjectNil,//{}
	notEmptyObject,//{}
	notEmptyArray;

notEmptyObject = {
	'0': {'00': 1,
			'01': 2,
			'02': 3},
	'1':	{'10': {'100': 4,
								'101': 5},
					'11': 6},
	'2':	7,
	'3':	{'30': 8,
				'31': {'310': 9}}
};
//*notEmptyObject['3']['31']['310'] => 9
//*notEmptyObject['newKey'] = 'newKeyValue'

notEmptyArray = [ 
	[1, 2, 3],
	[[4, 5], 6],
	7,
	[8, [9]]
];
//*notEmptyArray[1][0][0] => 4
//*a1[a1.length]=0 => 0

//implementation
function flattenObj(someObj, resultingObj) {
	//safety checked
	if (typeof someObj !== 'object') {
		return 'passed parameter is not an object';//exit
	}
	
	//default value
	if (!resultingObj) {
		resultingObj = {};
	}	
	
	//iterator ?
	for (var prop in someObj) {
		if( someObj.hasOwnProperty( prop ) ) {
			console.log("o." + prop + " = " + someObj[prop]);
			if (typeof someObj[prop] == 'object') {
				//return flaten(someObj[prop]);
				flattenObj(someObj[prop], resultingObj);
			} else {
				//must accumulate somehow
				//return someObj[prop];
				resultingObj[prop] = someObj[prop];
			}
		} 
	}
	//overall accumulated value
	return resultingObj;//?or new 'List'
}//*work as expected		

function isArray(myArray) {
    return myArray
			.constructor
				.toString()
					.indexOf("Array") > -1;
					//.item(0) !== null
}

function arrayIterator(someArray, 
	currentIndex, 
	resultingArray) {
	//empty array or index out of bound
	if( someArray[currentIndex]) {//exist / not undefined
		console.log("someArray.[" + currentIndex + "] = " + someArray[currentIndex]);		
		if ( isArray( someArray[currentIndex] ) )  {//complex Array
			//return flatten(someObj[prop]);
			//'currentIndex' fos subArray is = 0 (recursion)
			arrayIterator(someArray[currentIndex], 
				0, 
				resultingArray);
			//return from recursion and go to the next operator / expression
			//someArray[currentIndex]	isArray	so, it must be skipped	
			//& not added to the 'resultingArray'
			//to avoid this -- (currentIndex + 1)
			//currentIndex += 1;
				//if using 'flattenArray()' then non stop process / not terminate 	
		} else {			//not Array
			//must accumulate somehow
			resultingArray[resultingArray.length] = someArray[currentIndex];
			console.log("adding element " + someArray[currentIndex] + 
				" to 'resultingArray'");
		}					
		//boundary check			
		if( currentIndex < someArray.length - 1) {	
			//go / fetch next element in array (recursion)
			arrayIterator(someArray, 
				currentIndex + 1, 
				resultingArray);
		} 
	} else {
		//do nothing ?
		console.log(currentIndex + "-th element in someArray is undefined");
	} 
	//last operator / expression
	return resultingArray;
}//work as expected: [1, 2, 3, 4, 5, 6, 7, 8, 9]

function flattenArray(
	someArray, 
	resultingArray/*,
	currentIndex,//starting index
	overAllIndex*/) {
	//safety checked
	if (typeof someArray !== 'object') {
		return 'passed parameter is not an object';//exit
	} else {
		if (isArray(someArray)) {
		} else {	
			return 'passed object is not an Array';//exit
		}
	}	
	//default value
	if (!resultingArray) {
		resultingArray = [];//length = 0
	}		
	/*if (!currentIndex) {
		currentIndex = 0;//length = 0
	}*/		
	/*if (!overAllIndex) {
		overAllIndex = 0;//length = 0
	}	*/	
	//iterator ? instead of loop
	arrayIterator(someArray, 0, resultingArray);	
	//*arrayIterator(someArray, currentIndex, resultingArray);	
	//arrayIterator(notEmptyArray, 0, []);
	
	//overall accumulated value
	return resultingArray;
}//work as expected: [1, 2, 3, 4, 5, 6, 7, 8, 9]

function flattenArray2(
	someArray, 
	resultingArray/*,
	currentIndex*/) {
	//safety checked
	if (typeof someArray !== 'object') {
		return 'passed parameter is not an object';//exit
	} else {
		if (isArray(someArray)) {
		} else {	
			return 'passed object is not an Array';//exit
		}
	}
	
	//setting /checking default value
	if (!resultingArray) {
		resultingArray = [];//length = 0
	}		
	
	//iterator ?
	for (var index = 0, arrayLength = someArray.length; 
		index < arrayLength; 
		++index ) {
		//empty array
		if( someArray[index]) {//element exist / defined -- not undefined
			console.log("someArray.[" + index + "] = " + someArray[index]);
			//complex Array
			if ( isArray( someArray[index] ) )  {
				//'currentIndex' fos subArray is = 0 (recursion)
				flattenArray2(someArray[index], resultingArray);
			} else {
				//must accumulate not array elements as result somehow
				resultingArray[resultingArray.length] = someArray[index];
				console.log("adding element " + someArray[index] + 
					" to 'resultingArray'");
			}
		} else {
			//do nothing ?
				console.log(index + "-th element in someArray is undefined");
		} 
	}//end for loop
	
	//overall accumulated value
	return resultingArray;//?or new 'List'
}//work as expected