<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<style type="text/css">

#name {width: 80px;}
#sample4_postcode {width: 60px;}
#sample4_roadAddress {width: 250px;}
#sample4_jibunAddress {width:80px;}

.a {
	color: #111111;
	Font: 10px arial;
	Background: #FFFFFF;
	padding: 5px 30px;
	border: 1px solid;
}

.aa{
	Background:#F2F2F2;
	font:15px Arial;
    padding: 3px;
	border:none;
  	height: 22px;
}

form{
	position: relative; left:480px;
	top:10px;
	}
</style>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<script type="text/javascript">
	$(document).ready(function() {

		$("#passwd2").on("keyup", function() {
			if ($("#passwd").val() != $("#passwd2").val()) {
				$("#result2").text("비밀번호가 일치하지 않습니다.");
				$("#result2").css("color", "red");
				$("#passwd2").focus();

				return false;
			} else if ($("#passwd").val() == $("#passwd2").val()) {

				$("#result2").text("비밀번호가 동일합니다.");
				$("#result2").css("color", "blue");
			}

		});
	});


$(document).ready(function(){
	
	$("#emailSelect").click(function(){
		var email2=$("#emailSelect option:selected").val();
		$("#email2").val(email2);
		
	});
	
	
});



	function openConfirmid(e, input) {
		e.preventDefault();
		if (input.userid.value == "") {

			alert("아이디를 입력하지 않았습니다.")
			return;
		} else {
			input.action = "memberPeoAdd"
		}
		url = "Mem_Confirm?userid=" + input.userid.value;
		open(
				url,
				"confirm",
				"toolbar=no,location=no,status=no,menubar=no,scrollbars=no,resizable=no,width=310,height=180");
		//e.stopPropagation();

	}
</script>




<form action="memberPeoAdd" method="get">

	아이디:<input type="text" name="userid" id="userid" id="name" class="aa"> <span id="result" ></span>
		 <input type="submit" name="idCheck" value="중복확인"
				onclick="openConfirmid(event,this.form)"> <br><br>
	비밀번호:<input type="password" name="m_passwd" id="passwd" id="name" class="aa"><br><br>
	비빌번호확인:<input type="password" name="m_passwd2" id="passwd2" onkeyup="check()" id="name" class="aa"><span id="result2"></span> <br> <br>
	이름:<input type="text" name="m_username" id="name" class="aa"><br> <br>
	    <input type="text" name="m_post" id="sample4_postcode" placeholder="우편번호"> 
	    <input type="button" onclick="sample4_execDaumPostcode()" value="우편번호 찾기"><br>
		<input type="text" name="m_addr1" id="sample4_roadAddress" placeholder="도로명주소"> 
		<input type="text" name="m_addr2" id="sample4_jibunAddress" placeholder="지번주소"><span id="guide" style="color: #999"></span> <br><br> 
	전화번호:<select name="m_phone1">
			<option value="010">010</option>
			<option value="011">011</option>
		  </select>- <input type="text" name="m_phone2" id="name" class="aa">-<input type="text" name="m_phone3" id="name" class="aa"> <br><br>
	이메일:<input type="text" name="m_email1" id="email1" id="name" class="aa">@<input type="text" name="m_email2" id="email2" id="name" class="aa" placeholder="직접입력"> 
			<select id="emailSelect">
				<option value="daum.net">daum.net</option>
				<option value="naver.com">naver.com</option>
			</select> <br><br> 
	<input class="a" type="submit" value="회원가입"> 
	<input class="a" type="reset" value="취소"><br><br>

</form>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script>
	//본 예제에서는 도로명 주소 표기 방식에 대한 법령에 따라, 내려오는 데이터를 조합하여 올바른 주소를 구성하는 방법을 설명합니다.
	function sample4_execDaumPostcode() {
		new daum.Postcode(
				{
					oncomplete : function(data) {
						// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

						// 도로명 주소의 노출 규칙에 따라 주소를 조합한다.
						// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
						var fullRoadAddr = data.roadAddress; // 도로명 주소 변수
						var extraRoadAddr = ''; // 도로명 조합형 주소 변수

						// 법정동명이 있을 경우 추가한다. (법정리는 제외)
						// 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
						if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
							extraRoadAddr += data.bname;
						}
						// 건물명이 있고, 공동주택일 경우 추가한다.
						if (data.buildingName !== '' && data.apartment === 'Y') {
							extraRoadAddr += (extraRoadAddr !== '' ? ', '
									+ data.buildingName : data.buildingName);
						}
						// 도로명, 지번 조합형 주소가 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
						if (extraRoadAddr !== '') {
							extraRoadAddr = ' (' + extraRoadAddr + ')';
						}
						// 도로명, 지번 주소의 유무에 따라 해당 조합형 주소를 추가한다.
						if (fullRoadAddr !== '') {
							fullRoadAddr += extraRoadAddr;
						}

						// 우편번호와 주소 정보를 해당 필드에 넣는다.
						document.getElementById('sample4_postcode').value = data.zonecode; //5자리 새우편번호 사용
						document.getElementById('sample4_roadAddress').value = fullRoadAddr;
						document.getElementById('sample4_jibunAddress').value = data.jibunAddress;

						// 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
						if (data.autoRoadAddress) {
							//예상되는 도로명 주소에 조합형 주소를 추가한다.
							var expRoadAddr = data.autoRoadAddress
									+ extraRoadAddr;
							document.getElementById('guide').innerHTML = '(예상 도로명 주소 : '
									+ expRoadAddr + ')';

						} else if (data.autoJibunAddress) {
							var expJibunAddr = data.autoJibunAddress;
							document.getElementById('guide').innerHTML = '(예상 지번 주소 : '
									+ expJibunAddr + ')';

						} else {
							document.getElementById('guide').innerHTML = '';
						}
					}
				}).open();
	}
</script>