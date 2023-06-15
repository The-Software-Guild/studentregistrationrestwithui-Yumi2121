$(document).ready(function(){

    $("#update").hide();

    assignDataToTable();

    // update a stduent
    $('#studentTable').on('click', 'button[id="edit"]', function(e) {
        var id = $(this).closest('tr').children('td:first').text();
        var name = $(this).closest('tr').children('td:nth-child(2)').text(); 
        var age = $(this).closest('tr').children('td:nth-child(3)').text(); 
        var mobile = $(this).closest('tr').children('td:nth-child(4)').text(); 
        var address = $(this).closest('tr').children('td:nth-child(5)').text(); 

     
        $('#inputName').val(name);
        $('#inputAge').val(age);
        $('#inputMobile').val(mobile);
        $('#inputAddress').val(address);

        $("#update").show();
        $('#addStudent').hide();
        
        $("#update").click(function() {
            var ageNum = parseInt($("#inputAge").val());

            var jsonVar = {
                id: id,
                name: $("#inputName").val(),
                age: ageNum,
                mobile:  $('#inputMobile').val(), 
                address: $('#inputAddress').val()

            };

            $.ajax({
                type: 'PUT',
                data: JSON.stringify(jsonVar),
                contentType: "application/json",
                url: 'http://localhost:8080/students/' + id,
                
                success: function(student) {
                    alertUsing("updating " + student.name, true);

                    $("#update").hide();
                    $('#addStudent').show();

                    $('#inputName').val('');
                    $('#inputAge').val('');
                    $('#inputMobile').val('');
                    $('#inputAddress').val('');
                    assignDataToTable();
                }, 
                error: function(err) {
                    alert(err);
                }
             })

        })

    });


    // delete
    $('#studentTable').on('click', 'button[id="delete"]', function(e) {
        var id = $(this).closest('tr').children('td:first').text();

        $.ajax({
            type: 'DELETE',
            url: 'http://localhost:8080/students/' + id,
            success: function(id) {
                
                alertUsing("Selected stduent has been deleted succesfully!", true);
                assignDataToTable;
            },
            error: function() {
                alert('FAILURE to delete stduent!');
            }
        });
    });


    // Display student list
    $("button#getstudents").click(function(){
        assignDataToTable();
    });


     // function to save stduent from form
     $("button#addStudent").click(function(event){
        event.preventDefault();
    
        var name = $('#inputName').val();
        var age = $('#inputAge').val();
        var mobile = $('#inputMobile').val();
        var addr = $('#inputAddress').val();

        $.ajax({
            type: 'POST',
            contentType: "application/json; charset=utf-8",
            url: 'http://localhost:8080/students',
            dataType: "json",
            data: JSON.stringify({'name': name, 'age': age, 'mobile': mobile, 'address': addr}),
            success: function(student) {
                alertUsing(student.name + " added succesfully!", true);

            },
            error: function() {
                alertUsing("Please fill all the reqirement and try again!", false);
            }
        });     
     });

     //search by name
     $("button#search").click(function(e) {
        e.preventDefault();

        var name = $('#seach-name').val();
        var studnetsData = "";

        $.ajax({
            type: 'GET',
            url: 'http://localhost:8080/students/name/' + name,
            success: function(studentArray) {

                    studentArray.forEach(function(student) {
                        console.log(studentArray);
    
                        studnetsData += '<tr>' +
                        '<td>' + student.id + '</td>' +
                        '<td>' + student.name + '</td>' +
                        '<td>' + student.age + '</td>' +
                        '<td>' + student.mobile + '</td>' +
                        '<td>' + student.address + '</td>' +
                        '<td>' +
                        '<button id="edit" class="btn btn-success btn-sm rounded-0" type="button" data-toggle="tooltip" data-placement="top" title="Edit"><i class="fa fa-edit"></i></button>&nbsp &nbsp;' +
                        '<button id="delete" class="btn btn-danger btn-sm rounded-0" type="button" data-toggle="tooltip" data-placement="top" title="Delete"><i class="fa fa-trash"></i></button>' +
                        '</td>' +
    
                        '</tr>'   
                    });
                    $("#studentTable>tbody").html(studnetsData);
            },
            error: function() {
                alertUsing("Please make sure the name are correct and try again!", false)
            }
        });      
    });


     function assignDataToTable() {
        $('tbody').empty();
        var studnetsData = "";
        $.ajax({
            type: 'GET',
            url: 'http://localhost:8080/students',
            success: function(studentArray) {

                    studentArray.forEach(function(student) {
                    console.log(studentArray);

                    studnetsData += '<tr>' +
                    '<td>' + student.id + '</td>' +
                    '<td>' + student.name + '</td>' +
                    '<td>' + student.age + '</td>' +
                    '<td>' + student.mobile + '</td>' +
                    '<td>' + student.address + '</td>' +
                    '<td>' +
                    '<button id="edit" class="btn btn-success btn-sm rounded-0" type="button" data-toggle="tooltip" data-placement="top" title="Edit"><i class="fa fa-edit"></i></button>&nbsp &nbsp;' +
                    '<button id="delete" class="btn btn-danger btn-sm rounded-0" type="button" data-toggle="tooltip" data-placement="top" title="Delete"><i class="fa fa-trash"></i></button>' +
                    '</td>' +

                    '</tr>'   
                });
                $("#studentTable>tbody").html(studnetsData);
            },
            error: function(studentArray) {
                alert('FAILURE!');
                console.log(studentArray);
            }
        });
    }



     function alertUsing(text, flag) {

        var alert = $(".alert");
    
        if(flag){
            alert.removeClass("alert-danger").addClass("alert-success");
        }else{
            alert.removeClass("alert-success").addClass("alert-danger");
            
        }
        
        alert.fadeIn(400);
        alert.css("display", "block");
        alert.text(text);
        setTimeout(function() {
            alert.fadeOut();
        }, 2000);
    
      }




});