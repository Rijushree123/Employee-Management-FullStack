import React from 'react'
import { useState, useEffect } from 'react'
import { useNavigate, useParams } from 'react-router-dom'
import { addDepartment, getDepartment, updateDepartment } from '../services/DepartmentService'
const DepartmentComponent = () => {
    const [departmentName, setDepartmentName] = useState('')
        const [departmentDescription, setDepartmentDescription] = useState('')
        const [error, setError] = useState('')
        const navigate = useNavigate()
        const {id} = useParams()
        useEffect(() => {
          if(id){
            getDepartment(id).then((response)=>{
                setDepartmentName(response.data.departmentName)
                setDepartmentDescription(response.data.departmentDescription)
            })
            .catch((error) => console.error(error))
          }
        }, [id])
        
        const saveDepartment = (e) => {
            e.preventDefault()
            if (validForm()) {
                const department = { departmentName, departmentDescription }
                console.log(department)
                addDepartment(department)
                    .then((response) => {
                        console.log(response.data)
                        navigate("/departments")
                    })
                    .catch((error) => console.error(error))
            }
        }
        const editDepartment = (e) =>{
            e.preventDefault()
            if (validForm()) {
                const department = { departmentName, departmentDescription }
                console.log(department)
                updateDepartment(id, department)
                    .then((response) => {
                        console.log(response.data)
                        navigate("/departments")
                    })
                    .catch((error) => console.error(error))
            }
        }
        const validForm = () => {
            let valid = true
            const errorsCopy = {}
            if (departmentName.trim()) {
                errorsCopy.departmentName = ''
            } else {
                errorsCopy.departmentName = "Department Name is required"
                valid = false
            }
            if (departmentDescription.trim()) {
                errorsCopy.departmentDescription = ''
            } else {
                errorsCopy.departmentDescription = "Department Description is required"
                valid = false
            }
            
            setError(errorsCopy)
            console.log("Valid:"+valid)
            return valid
        }
    
        const pageTitle = () =>{
            if(id){
                return <h2 className='text-center'>Edit Department</h2>
            }
            else{
                return <h2 className='text-center'>Add Department</h2>
            }
        }
  return (
    <div className='container my-5'>
            <div className='row'>
                <div className='card col-md-6 offset-md-3 offset-md-3'>
                    {pageTitle()}
                    <div className='card-body'>
                        <form>
                            <div className='form-group mb-2'>
                                <label className='form-label'>Department Name: </label>
                                <input type='text'
                                    placeholder='Enter Department Name'
                                    name='departmentName'
                                    value={departmentName}
                                    className={`form-control ${error.departmentName?'is-invalid':''}`}
                                    onChange={(e) => setDepartmentName(e.target.value)}>
                                </input>
                                {error.departmentName && <div className='invalid-feedback'>{error.departmentName}</div>}
                            </div>
                            <div className='form-group mb-2'>
                                <label className='form-label'>Department Description: </label>
                                <input type='text'
                                    placeholder='Enter Department Description'
                                    name='departmentDescription'
                                    value={departmentDescription}
                                    className={`form-control ${error.departmentDescription?'is-invalid':''}`}
                                    onChange={(e) => setDepartmentDescription(e.target.value)}>
                                </input>
                                {error.departmentDescription && <div className='invalid-feedback'>{error.departmentDescription}</div>}
                            </div>
                            <button className='btn btn-success' onClick={(id)?editDepartment:saveDepartment}>Submit</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
  )
}

export default DepartmentComponent