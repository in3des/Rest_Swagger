// console.log("Hello from our JS")



// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

// ================================================ FETCH USER TABLE ================================================

// const mainTableList = document.querySelector('.main-table-list');
let output2 = '';

const renderUserTable = (user) => {
    console.log("7th")
    output2 += `
        <table class="table table-hover table-striped">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Age</th>
                    <th>Email</th>
                    <th>Role</th>
                </tr>
            </thead>

            <tbody>
              
                <tr>
                    <td>${user.id}</td>
                    <td>${user.name}</td>
                    <td>${user.surname}</td>
                    <td>${user.age}</td>
                    <td>${user.email}</td>
                    <td>`
    user.roles.forEach(val => {
        console.log(val)
        output2 += `
                        ${val.role.substring(5)}
                        `
    })

    output2 += `
                    </td>
                </tr>                
            </tbody>

        </table>
        `

    document.getElementById('user-table-list').innerHTML = output2;
}


fetch("api/findlogged")
    .then(res => res.json())
    .then(data => renderUserTable(data))

// ================================================ FETCH USER TABLE ================================================


