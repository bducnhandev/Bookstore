$(document).ready(function() {
    // Fetch user roles
    $.ajax({
        url: 'http://localhost:8080/api/v1/roles',
        type: 'GET',
        dataType: 'json',
        success: function(userRoles) {
            let isAdmin = userRoles.includes('ADMIN');

            // Fetch data from API
            $.ajax({
                url: 'http://localhost:8080/api/v1/books',
                type: 'GET',
                dataType: 'json',
                success: function(data) {
                    let trHTML = '';
                    $.each(data, function(i, item) {
                        trHTML += '<tr id="book-' + item.id + '">' +
                                        '<td>' + item.id + '</td>' +
                                        '<td>' + item.title + '</td>' +
                                        '<td>' + item.author + '</td>' +
                                        '<td>' + item.price + '</td>' +
                                        '<td>' + item.category + '</td>';
                        if (isAdmin) {
                            trHTML += '<td>' +
                                        '<a href="#' + item.id + '" class="text-primary" onclick="apiUpdateBook(' + item.id + ', this); return false;">Edit</a> | ' +
                                        '<a href="/books" ' + item.id + ' class="text-danger" onclick="apiDeleteBook(' + item.id + ', this); return false;">Delete</a>' +
                                      '</td>';
                        }
                        trHTML += '</tr>';
                    });
                    $('#book-table-body').append(trHTML);
                }
            });
        }
    });
});

// Delete book via API
function apiDeleteBook(id) {
    if (confirm('Are you sure you want to delete this book?')) {
        $.ajax({
            url: 'http://localhost:8080/api/v1/books/' + id,
            type: 'DELETE',
            success: function() {
                alert('Book deleted successfully!');
                $('#book-' + id).remove();
            },
            error: function(xhr, textStatus, errorThrown) {
                // Handle errors
                alert('Error deleting book: ' + errorThrown);
            }
        });
    }
}

function apiUpdateBook(id) {
    if (confirm('Bạn có chắc chắn muốn chỉnh sửa cuốn sách này không?')) {
        // Lấy thông tin hiện tại của sách
        $.ajax({
            url: 'http://localhost:8080/api/v1/books/' + id,
            type: 'GET',
            dataType: 'json',
            success: function(book) {
                // Điền thông tin vào biểu mẫu
                $('#edit-book-id').val(book.id);
                $('#edit-book-title').val(book.title);
                $('#edit-book-author').val(book.author);
                $('#edit-book-price').val(book.price);
                $('#edit-book-category').val(book.category);

                // Hiển thị biểu mẫu chỉnh sửa
                showEditForm();
            },
            error: function(xhr, textStatus, errorThrown) {
                alert('Lỗi khi lấy thông tin sách: ' + errorThrown);
            }
        });
    }
}

function showEditForm() {
    $('#edit-book-modal').show(); // Hiển thị modal chứa biểu mẫu chỉnh sửa
}

function hideEditForm() {
    $('#edit-book-modal').hide(); // Ẩn modal chứa biểu mẫu chỉnh sửa
}

function submitBookUpdate() {
    var book = {
        id: $('#edit-book-id').val(),
        title: $('#edit-book-title').val(),
        author: $('#edit-book-author').val(),
        price: $('#edit-book-price').val(),
        category: $('#edit-book-category').val()
    };

    $.ajax({
        url: 'http://localhost:8080/api/v1/books/' + book.id,
        type: 'PUT',
        contentType: 'application/json',
        data: JSON.stringify(book),
        success: function() {
            alert('Cập nhật sách thành công!');
            location.reload(); // Tải lại trang để thấy thay đổi
        },
        error: function(xhr, textStatus, errorThrown) {
            alert('Lỗi khi cập nhật sách: ' + errorThrown);
        }
    });
}
