import React from 'react'
import { useState, useEffect } from 'react'
import { listEmployees, deleteEmployee, updateEmployee } from '../services/EmployeeService'
import { useNavigate } from 'react-router-dom'

const ListEmployeeComponent = () => {
    // const dummyData = [
    //     {
    //     "id":1,
    //     "firstName":"Rijushree",
    //     "lastName":"Guha",
    //     "email":"riju@gmail.com"
    //     },
    //     {
    //         "id":2,
    //         "firstName":"Suzan",
    //         "lastName":"Li",
    //         "email":"li@gmail.com"
    //      },
    //      {
    //         "id":3,
    //         "firstName":"Charles",
    //         "lastName":"Dikens",
    //         "email":"diken@gmail.com"
    //      }
    // ]
    const [employees, setEmployees] = useState([])
    useEffect(() => {
        getAllEmployees()
    }, [])

    const navigate = useNavigate()
    const getAllEmployees = () => {
        listEmployees().then((response) => {
            setEmployees(response.data)
        }).catch(error => {
            console.error(error)
        })
    }
    const addNewEmployee = () => {
        navigate('/add-employee')
    }
    const editEmployee = (id) => {
        navigate(`/edit-employee/${id}`)
    }
    const handleDeleteEmployee = (id) => {
        deleteEmployee(id).then((response) => {
            getAllEmployees()
        }).catch(error => {
            console.error(error)
        })
    }

    return (
        <div className='container'>
            <h2 className='text-center'>List of Employees</h2>
            <button className='btn btn-primary mb-2' onClick={addNewEmployee}>Add Employee</button>
            <table className='table table-striped table-bordered'>
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Email Id</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    {
                        employees.map(empObj =>
                            <tr key={empObj.id}>
                                <td>{empObj.id}</td>
                                <td>{empObj.firstName}</td>
                                <td>{empObj.lastName}</td>
                                <td>{empObj.email}</td>
                                <td>
                                    <button className='btn btn-success mx-2' onClick={() => editEmployee(empObj.id)}>Edit</button>
                                    <button className='btn btn-danger mx-2' onClick={() => handleDeleteEmployee(empObj.id)}>Delete</button>
                                </td>
                            </tr>
                        )
                    }
                </tbody>
            </table>
        </div>
    )
}

export default ListEmployeeComponent