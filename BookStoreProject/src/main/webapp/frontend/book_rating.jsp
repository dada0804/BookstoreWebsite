<c:forTokens items="${book.ratingStars}" delims = "," var = "star">
						<c:if test ="${star eq 'on'}">
							<img src = "images/rating_on.pic"/>
						</c:if>
						<c:if test ="${star eq 'half'}">
							<img src = "images/rating_half.jpeg"/>
						</c:if>
						<c:if test ="${star eq 'off'}">
							<img src = "images/rating_off.pic"/>
						</c:if>
					</c:forTokens>			