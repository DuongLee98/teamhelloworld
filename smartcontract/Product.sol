pragma solidity ^0.4.11;

contract Product
{
    mapping (string => string) name;
    mapping (string => uint256) price;
    string [] ids;
    
    function addProduct(string _id, string _name, uint256 _price) public payable returns(bool)
    {
        bytes memory tmp = bytes(name[_id]);
        require(tmp.length == 0);
        name[_id] = _name;
        price[_id] = _price;
        ids.push(_id);
        return true;
    }
    
    function getName(string _id) constant public returns(string)
    {
        bytes memory tmp = bytes(name[_id]);
        require(tmp.length != 0);
        return name[_id];
    }
    
    function getPrice(string _id) constant public returns(uint256)
    {
        bytes memory tmp = bytes(name[_id]);
        require(tmp.length != 0);
        return price[_id];
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
