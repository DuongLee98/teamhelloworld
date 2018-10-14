pragma solidity ^0.4.11;

contract Level
{
    mapping (string => string) name;
    string [] ids;
    
    function addLevel(string _id, string _name) public payable returns(bool)
    {
        bytes memory tmp = bytes(name[_id]);
        require(tmp.length == 0);
        name[_id] = _name;
        ids.push(_id);
        return true;
    }
    
    function getName(string _id) constant public returns(string)
    {
        bytes memory tmp = bytes(name[_id]);
        require(tmp.length != 0);
        return name[_id];
    }
    
    
    function getId(uint256 i) constant public returns(string)
    {
        require(i>=0 && i<=ids.length-1);
        return ids[i];
    }
    
    function getSizeid () constant public returns(uint256)
    {
        return ids.length;
    }
}
