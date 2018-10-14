pragma solidity ^0.4.11;

contract Account
{
    mapping (string => string) pass;
    mapping (string => string) phone;
    mapping (string => bool) has;
    string [] users;
    
    function addAccount(string _user, string _pass, string _phone) public payable returns(bool)
    {
        if (checkHasuser(_user) == true) return false;
        pass[_user] = _pass;
        phone[_user] = _phone;
        has[_user] = true;
        users.push(_user);
        return true;
    }
    
    function checkHasuser(string x) constant public returns(bool)
    {
        return has[x];
    }
    
    function getPassword(string _user) constant public returns(string)
    {
        if (checkHasuser(_user) == false) return "";
        return pass[_user];
    }
    
    function getPhone(string _user) constant public returns(string)
    {
        if (checkHasuser(_user) == false) return "";
        return phone[_user];
    }
    
    function getUser(uint256 i) constant public returns(string)
    {
        require(i>=0 && i<=users.length-1);
        return users[i];
    }
    
    function getSizeusers () constant public returns(uint256)
    {
        return users.length;
    }
}
