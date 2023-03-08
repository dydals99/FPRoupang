    const starSize1 = 30, maxStar1 = 5, gutter1 = 2;//별 크기, 별 개수
    let maskMax1 = 0; //오버레이 마스크 최대 너비
    window.addEventListener('DOMContentLoaded',()=>{
        for(let i = 0;i < maxStar1;i++){
            let el = document.createElement('div');
            el.style.width = starSize1 + 'px';
            el.style.height = starSize1 + 'px';
            el.style.marginRight = gutter1 + 'px';
            el.innerHTML = '<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 576 512"><path fill="none" class="starcolor1" d="M381.2 150.3L524.9 171.5C536.8 173.2 546.8 181.6 550.6 193.1C554.4 204.7 551.3 217.3 542.7 225.9L438.5 328.1L463.1 474.7C465.1 486.7 460.2 498.9 450.2 506C440.3 513.1 427.2 514 416.5 508.3L288.1 439.8L159.8 508.3C149 514 135.9 513.1 126 506C116.1 498.9 111.1 486.7 113.2 474.7L137.8 328.1L33.58 225.9C24.97 217.3 21.91 204.7 25.69 193.1C29.46 181.6 39.43 173.2 51.42 171.5L195 150.3L259.4 17.97C264.7 6.954 275.9-.0391 288.1-.0391C300.4-.0391 311.6 6.954 316.9 17.97L381.2 150.3z"/></svg>';
            document.querySelector('.rating1').appendChild(el);
        }
        
        maskMax1 = parseInt(starSize1 * maxStar1 + gutter1 * (maxStar1-1));//최대 마스크 너비 계산
        document.querySelector('input[name=ratevalue1]').max = maxStar1;//입력 필드 최대값 재설정
        setRating(document.querySelector('input[name=ratevalue1]').value);//초기 별점 마킹

        function setRating(val = 0){
            document.querySelector('.overlay1').style.width = parseInt(maskMax1 - val * starSize1 - Math.floor(val) * gutter1) + 'px';//마스크 크기 변경해서 별점 마킹
        }
        
    })
    const starSize2 = 30, maxStar2 = 5, gutter2 = 2;//별 크기, 별 개수
    let maskMax2 = 0; //오버레이 마스크 최대 너비
    window.addEventListener('DOMContentLoaded',()=>{
        for(let i = 0;i < maxStar2;i++){
            let el = document.createElement('div');
            el.style.width = starSize2 + 'px';
            el.style.height = starSize2 + 'px';
            el.style.marginRight = gutter2 + 'px';
            el.innerHTML = '<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 576 512"><path fill="none" class="starcolor2" d="M381.2 150.3L524.9 171.5C536.8 173.2 546.8 181.6 550.6 193.1C554.4 204.7 551.3 217.3 542.7 225.9L438.5 328.1L463.1 474.7C465.1 486.7 460.2 498.9 450.2 506C440.3 513.1 427.2 514 416.5 508.3L288.1 439.8L159.8 508.3C149 514 135.9 513.1 126 506C116.1 498.9 111.1 486.7 113.2 474.7L137.8 328.1L33.58 225.9C24.97 217.3 21.91 204.7 25.69 193.1C29.46 181.6 39.43 173.2 51.42 171.5L195 150.3L259.4 17.97C264.7 6.954 275.9-.0391 288.1-.0391C300.4-.0391 311.6 6.954 316.9 17.97L381.2 150.3z"/></svg>';
            document.querySelector('.rating2').appendChild(el);
        }
        
        maskMax2 = parseInt(starSize2 * maxStar2 + gutter2 * (maxStar2-1));//최대 마스크 너비 계산
        document.querySelector('input[name=ratevalue2]').max = maxStar2;//입력 필드 최대값 재설정
        setRating(document.querySelector('input[name=ratevalue2]').value);//초기 별점 마킹

        function setRating(val = 0){
            document.querySelector('.overlay2').style.width = parseInt(maskMax2 - val * starSize2 - Math.floor(val) * gutter2) + 'px';//마스크 크기 변경해서 별점 마킹
        }
        
    })
    const starSize3 = 30, maxStar3 = 5, gutter3 = 2;//별 크기, 별 개수
    let maskMax3 = 0; //오버레이 마스크 최대 너비
    window.addEventListener('DOMContentLoaded',()=>{
        for(let i = 0;i < maxStar3;i++){
            let el = document.createElement('div');
            el.style.width = starSize3 + 'px';
            el.style.height = starSize3 + 'px';
            el.style.marginRight = gutter3 + 'px';
            el.innerHTML = '<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 576 512"><path fill="none" class="starcolor3" d="M381.2 150.3L524.9 171.5C536.8 173.2 546.8 181.6 550.6 193.1C554.4 204.7 551.3 217.3 542.7 225.9L438.5 328.1L463.1 474.7C465.1 486.7 460.2 498.9 450.2 506C440.3 513.1 427.2 514 416.5 508.3L288.1 439.8L159.8 508.3C149 514 135.9 513.1 126 506C116.1 498.9 111.1 486.7 113.2 474.7L137.8 328.1L33.58 225.9C24.97 217.3 21.91 204.7 25.69 193.1C29.46 181.6 39.43 173.2 51.42 171.5L195 150.3L259.4 17.97C264.7 6.954 275.9-.0391 288.1-.0391C300.4-.0391 311.6 6.954 316.9 17.97L381.2 150.3z"/></svg>';
            document.querySelector('.rating3').appendChild(el);
        }
        
        maskMax3 = parseInt(starSize3 * maxStar3 + gutter3 * (maxStar3-1));//최대 마스크 너비 계산
        document.querySelector('input[name=ratevalue3]').max = maxStar3;//입력 필드 최대값 재설정
        setRating(document.querySelector('input[name=ratevalue3]').value);//초기 별점 마킹

        function setRating(val = 0){
            document.querySelector('.overlay3').style.width = parseInt(maskMax3 - val * starSize3 - Math.floor(val) * gutter3) + 'px';//마스크 크기 변경해서 별점 마킹
        }
        
    })
    const starSize4 = 30, maxStar4 = 5, gutter4 = 2;//별 크기, 별 개수
    let maskMax4 = 0; //오버레이 마스크 최대 너비
    window.addEventListener('DOMContentLoaded',()=>{
        for(let i = 0;i < maxStar4;i++){
            let el = document.createElement('div');
            el.style.width = starSize4 + 'px';
            el.style.height = starSize4 + 'px';
            el.style.marginRight = gutter4 + 'px';
            el.innerHTML = '<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 576 512"><path fill="none" class="starcolor4" d="M381.2 150.3L524.9 171.5C536.8 173.2 546.8 181.6 550.6 193.1C554.4 204.7 551.3 217.3 542.7 225.9L438.5 328.1L463.1 474.7C465.1 486.7 460.2 498.9 450.2 506C440.3 513.1 427.2 514 416.5 508.3L288.1 439.8L159.8 508.3C149 514 135.9 513.1 126 506C116.1 498.9 111.1 486.7 113.2 474.7L137.8 328.1L33.58 225.9C24.97 217.3 21.91 204.7 25.69 193.1C29.46 181.6 39.43 173.2 51.42 171.5L195 150.3L259.4 17.97C264.7 6.954 275.9-.0391 288.1-.0391C300.4-.0391 311.6 6.954 316.9 17.97L381.2 150.3z"/></svg>';
            document.querySelector('.rating4').appendChild(el);
        }
        
        maskMax4 = parseInt(starSize4 * maxStar4 + gutter4 * (maxStar4-1));//최대 마스크 너비 계산
        document.querySelector('input[name=ratevalue4]').max = maxStar4;//입력 필드 최대값 재설정
        setRating(document.querySelector('input[name=ratevalue4]').value);//초기 별점 마킹

        function setRating(val = 0){
            document.querySelector('.overlay4').style.width = parseInt(maskMax4 - val * starSize4 - Math.floor(val) * gutter4) + 'px';//마스크 크기 변경해서 별점 마킹
        }
        
    })