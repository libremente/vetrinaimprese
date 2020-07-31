
function setCheckInsert()
{
	$(window).on('beforeunload',function windowBeforeUnload() {
	    return "system message";
	});
}

function removeCheckInsert()
{
	$(window).off('beforeunload');
}


function setCheckModify(saveButton,cancelButton,deleteButton,icheckArray,summernoteArray)
{
	if (saveButton)
		addSkeepElement(saveButton);
	if (cancelButton)
		addSkeepElement(cancelButton);
	if (deleteButton)
		addSkeepElement(deleteButton);
	
	if (icheckArray)
	{
		icheckArray.forEach(function (item, index, array) {
			  setCheckboxModifyElement(item);
		});
	}
	
	if (summernoteArray)
	{
		summernoteArray.forEach(function (item, index, array) {
			  setSummernoteModifyElement(item);
		});
	}
	
	$('.element-tag').on('classChange', function() {
		$(window).on('beforeunload',function windowBeforeUnload() {
		    return "system message";
		});
	});
	
	setInputModifyElement();
}

function addSkeepElement(element) {
	$(document).on('click', '#' + element, function offBeforeUnload(event) {
		$(window).off('beforeunload');
	});
}


function setCheckboxModifyElement(element) {

	$('#' + element).on('ifChecked', function(event) {
		$(window).on('beforeunload',function windowBeforeUnload() {
			    return "system message";
		});
	});
	
	$('#' + element).on('ifUnchecked', function(event) {
		$(window).on('beforeunload',function windowBeforeUnload() {
			    return "system message";
		});
	});
	
}


function setSummernoteModifyElement(element) {
	$('#' + element).summernote('onChangeListener',function() {
		$(window).on('beforeunload',function windowBeforeUnload() {
		    return "system message";
		});
	});
}

function setInputModifyElement() {

	$("form :input").change(function() {
			$(this).closest('form').data('changed', true);
			$(window).on('beforeunload',function windowBeforeUnload() {
			    return "system message";
		});
	});
}