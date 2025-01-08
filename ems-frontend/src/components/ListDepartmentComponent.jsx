import React from 'react'
import { deleteDepartment, listDepartments } from '../services/DepartmentService'
import { useState, useEffect } from 'react'
import { useNavigate } from 'react-router-dom'

const ListDepartmentComponent = () => {
    const [deparments, setDepartments] = useState([])
    useEffect(() => {
        getAllDepartments()
    }, [])

    const navigate = useNavigate()
    const getAllDepartments = () => {
        listDepartments().then((response) => {
            setDepartments(response.data)
        }).catch(error => {
            console.error(error)
        })
    }
    const addNewDepartment = () => {
        navigate('/add-department')
    }
    const editDepartment = (id) => {
        navigate(`/edit-department/${id}`)
    }
    const handleDeleteDepartment = (id) => {
        deleteDepartment(id).then((response) => {
            getAllDepartments()
        }).catch(error => {
            console.error(error)
        })
    }
  return (
    <div className='container'>
            <h2 className='text-center'>List of Department</h2>
            <button className='btn btn-primary mb-2' onClick={addNewDepartment}>Add Department</button>
            <table className='table table-striped table-bordered'>
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Department Name</th>
                        <th>Department Description</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    {
                        deparments.map(depObj =>
                            <tr key={depObj.id}>
                                <td>{depObj.id}</td>
                                <td>{depObj.departmentName}</td>
                                <td>{depObj.departmentDescription}</td>
                                <td>
                                    <button className='btn btn-success mx-2' onClick={() => editDepartment(depObj.id)}>Edit</button>
                                    <button className='btn btn-danger mx-2' onClick={() => handleDeleteDepartment(depObj.id)}>Delete</button>
                                </td>
                            </tr>
                        )
                    }
                </tbody>
            </table>
        </div>
  )
}

export default ListDepartmentComponent