function dateRangePickerLangs(currentLang){
	if(currentLang == 'en_US'){
		return {
	        "format": "MM/DD/YYYY",
	        "separator": " - ",
	        "applyLabel": "Apply",
	        "cancelLabel": "Cancel",
	        "fromLabel": "From",
	        "toLabel": "To",
	        "weekLabel": 'W',
	        "customRangeLabel": "Custom",
	        "daysOfWeek": [
	            "Su",
	            "Mo",
	            "Tu",
	            "We",
	            "Th",
	            "Fr",
	            "Sa"
	        ],
	        "monthNames": [
	            "January",
	            "February",
	            "March",
	            "April",
	            "May",
	            "June",
	            "July",
	            "August",
	            "September",
	            "October",
	            "November",
	            "December"
	        ],
	        "firstDay": 1
	    }
	}else if(currentLang == 'it_IT'){
		return {
	        "format": "MM/DD/YYYY",
	        "separator": " - ",
	        "applyLabel": "Applica",
	        "cancelLabel": "Cancella",
	        "fromLabel": "Da",
	        "toLabel": "A",
	        "weekLabel": 'S',
	        "customRangeLabel": "Periodo custom",
	        "daysOfWeek": [
	            "Do",
	            "Lu",
	            "Ma",
	            "Me",
	            "Gio",
	            "Ve",
	            "Sa"
	        ],
	        "monthNames": [
	            "Gennaio",
	            "Febbraio",
	            "Marzo",
	            "Aprile",
	            "Maggio",
	            "Giugno",
	            "Luglio",
	            "Agosto",
	            "Settembre",
	            "Ottobre",
	            "Novembre",
	            "Dicembre"
	        ],
	        "firstDay": 1
	    }
	}else{
		return {
	        "format": "MM/DD/YYYY",
	        "separator": " - ",
	        "applyLabel": "Applica",
	        "cancelLabel": "Cancella",
	        "fromLabel": "Da",
	        "toLabel": "A",
	        "customRangeLabel": "Periodo custom",
	        "daysOfWeek": [
	            "Do",
	            "Lu",
	            "Ma",
	            "Me",
	            "Gio",
	            "Ve",
	            "Sa"
	        ],
	        "monthNames": [
	            "Gennaio",
	            "Febbraio",
	            "Marzo",
	            "Aprile",
	            "Maggio",
	            "Giugno",
	            "Luglio",
	            "Agosto",
	            "Settembre",
	            "Ottobre",
	            "Novembre",
	            "Dicembre"
	        ],
	        "firstDay": 1
	    }		
	}
}
