var mapGoogle;
var positionMarker;


$(function () {

    map();

});


function removePointToMap()
{
	if (positionMarker)
		positionMarker.setMap(null);
}


function addPointToMap(latInput,lonInput)
{
	if (positionMarker)
		positionMarker.setMap(null);
	
	if (latInput)
	{
		positionMarker = mapGoogle.addMarker({
			lat: latInput,
			lng: lonInput
		});
		
		mapGoogle.panTo(new google.maps.LatLng(latInput, lonInput));
	}
}


function map() {
	
	
	var $map = $('#map');
	
	var latInput =  $map.data('latitude');
	var lonInput =  $map.data('longitude');
	
	var nopoint =  $map.data('nopoint');
	
	
	if (!latInput)
	{
		latInput = 44.411282;
		lonInput = 8.932549;
	}
	

    var styles = [{"featureType": "landscape", "stylers": [{"saturation": -100}, {"lightness": 65}, {"visibility": "on"}]}, {"featureType": "poi", "stylers": [{"saturation": -100}, {"lightness": 51}, {"visibility": "simplified"}]}, {"featureType": "road.highway", "stylers": [{"saturation": -100}, {"visibility": "simplified"}]}, {"featureType": "road.arterial", "stylers": [{"saturation": -100}, {"lightness": 30}, {"visibility": "on"}]}, {"featureType": "road.local", "stylers": [{"saturation": -100}, {"lightness": 40}, {"visibility": "on"}]}, {"featureType": "transit", "stylers": [{"saturation": -100}, {"visibility": "simplified"}]}, {"featureType": "administrative.province", "stylers": [{"visibility": "off"}]}, {"featureType": "water", "elementType": "labels", "stylers": [{"visibility": "on"}, {"lightness": -25}, {"saturation": -100}]}, {"featureType": "water", "elementType": "geometry", "stylers": [{"hue": "#ffff00"}, {"lightness": -25}, {"saturation": -97}]}];
    mapGoogle = new GMaps({
	el: '#map',
	lat: latInput,
	lng: lonInput,
	zoom: 12,
	zoomControl: true,
	zoomControlOpt: {
	    style: 'SMALL',
	    position: 'TOP_LEFT'
	},
	gestureHandling: 'cooperative',
	styles: styles
    });

    
    if (!nopoint)
    {
    	positionMarker = mapGoogle.addMarker({
    		lat: latInput,
    		lng: lonInput
    	});
    }
}