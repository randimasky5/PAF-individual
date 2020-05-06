$(document).ready(function()
{
	$("#alertSuccess").hide();
	$("#alertError").hide();
});

//SAVE ============================================
$(document).on("click", "#btnSave", function(event)
{
	// Clear alerts---------------------
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();
	
	// Form validation-------------------
	var status = validateTestForm();
	
	if (status != true)
	{
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}
	
	// If valid------------------------
	var method = ($("#IDSave").val() == "") ? "POST" : "PUT";
	
	$.ajax(
	{
		url : "TestsAPI",
		type : method,
		data : $("#formItem").serialize(),
		dataType : "text",
		complete : function(response, status)
		{
			onItemSaveComplete(response.responseText, status);
		}
	});
});

function onItemSaveComplete(response, status)
{
	if (status == "success")
	{
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success")
		{
			$("#alertSuccess").text("Successfully saved.");
			$("#alertSuccess").show();
			$("#divItemsGrid").html(resultSet.data);
		} 
		else if (resultSet.status.trim() == "error")
		{
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} 
	else if (status == "error")
	{
		$("#alertError").text("Error while saving.");
		$("#alertError").show();
	} 
	else
	{
		$("#alertError").text("Unknown error while saving..");
		$("#alertError").show();
	}
	
	$("#IDSave").val("");
	$("#formItem")[0].reset();
}


//UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
{
	$("#IDSave").val($(this).closest("tr").find('#IDUpdate').val());
	$("#testName").val($(this).closest("tr").find('td:eq(0)').text());
	$("#testCost").val($(this).closest("tr").find('td:eq(1)').text());
	$("#testDesc").val($(this).closest("tr").find('td:eq(2)').text());
	$("#labNo").val($(this).closest("tr").find('td:eq(3)').text());
	$("#hosname").val($(this).closest("tr").find('td:eq(4)').text());
});


//REMOVE==========================================
$(document).on("click", ".btnRemove", function(event)
{
	$.ajax(
	{
		url : "TestsAPI",
		type : "DELETE",
		data : "itemID=" + $(this).data("itemid"),
		dataType : "text",
		complete : function(response, status)
		{
			onItemDeleteComplete(response.responseText, status);
		}
	});
});

function onItemDeleteComplete(response, status)
{
	if (status == "success")
	{
		var resultSet = JSON.parse(response);
		
		if (resultSet.status.trim() == "success")
		{
			$("#alertSuccess").text("Successfully deleted.");
			$("#alertSuccess").show();
			$("#divItemsGrid").html(resultSet.data);
		} 
		else if (resultSet.status.trim() == "error")
		{
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} 
	else if (status == "error")
	{
		$("#alertError").text("Error while deleting.");
		$("#alertError").show();
	} 
	else
	{
		$("#alertError").text("Unknown error while deleting..");
		$("#alertError").show();
	}
}


function validateTestForm()
{
	//name
	if ($("#testName").val().trim() == "")
	{
		return "Insert Test Name.";
	}
	
	// lab no
	if ($("#labNo").val().trim() == "")
	{
		return "Insert Test Cost.";
	}
	// hospital name
	if ($("#hosname").val().trim() == "")
	{
		return "Insert Hospital Name.";
	}
	
	//PRICE-------------------------------
	if ($("#testCost").val().trim() == "")
	{
		return "Insert Test Cost.";
	}
	
	// is numerical value
	var tmpPrice = $("#testCost").val().trim();
	
	if (!$.isNumeric(tmpPrice))
	{
		return "Insert a numerical value for Test Cost.";
	}
	
	// convert to decimal price
	$("#testCost").val(parseFloat(tmpPrice).toFixed(2));
	
	// DESCRIPTION------------------------
	if ($("#testDesc").val().trim() == "")
	{
		return "Insert Test Description.";
	}
	
	return true;
}