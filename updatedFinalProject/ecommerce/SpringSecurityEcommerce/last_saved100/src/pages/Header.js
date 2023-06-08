import './Add.css'

function Header({setAdd}){
    return(
        <header>
            <div className="btn-move">
                <button className="addbtn" onClick={() => setAdd(true)}>Add</button>
            </div>
        </header>
    )
}

export default Header;