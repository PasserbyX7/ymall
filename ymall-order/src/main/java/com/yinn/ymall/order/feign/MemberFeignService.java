package com.yinn.ymall.order.feign;

import java.util.List;

import com.yinn.ymall.common.api.R;
import com.yinn.ymall.order.dto.MemberAddressDTO;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("member-service")
public interface MemberFeignService {

    @GetMapping("/api/member/v1/member-addresses/members/{memberId}")
    R<List<MemberAddressDTO>> listMemberAddressesByMemberId(@PathVariable Long memberId);

    @GetMapping("/api/member/v1/member-addresses/{memberAddressId}")
    R<MemberAddressDTO> getMemberAddressById(@PathVariable Long memberAddressId);
}

