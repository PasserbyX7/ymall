package com.yinn.ymall.third.service.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import com.aliyun.oss.OSS;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.MatchMode;
import com.aliyun.oss.model.PolicyConditions;
import com.yinn.ymall.third.dto.SignatureDTO;
import com.yinn.ymall.third.service.OssService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class OssServiceImpl implements OssService {

    @Autowired
    private OSS client;

    @Value("${spring.cloud.alicloud.oss.endpoint}")
    private String endpoint;

    @Value("${spring.cloud.alicloud.oss.bucket}")
    private String bucket;

    @Value("${spring.cloud.alicloud.access-key}")
    private String accessId;

    @Override
    public SignatureDTO policy() {
        // host的格式为 bucketName.endpoint
        var host = String.format("https://%s.%s", bucket, endpoint);
        // 用户上传文件前缀
        String dir = LocalDate.now().format(DateTimeFormatter.ISO_DATE) + "/";
        SignatureDTO signatureDTO = null;
        try {
            long expireTime = 30;
            long expireEndTime = System.currentTimeMillis() + expireTime * 1000;
            Date expiration = new Date(expireEndTime);
            PolicyConditions policyConds = new PolicyConditions();
            policyConds.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, 1048576000);
            policyConds.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_KEY, dir);

            String postPolicy = client.generatePostPolicy(expiration, policyConds);
            byte[] binaryData = postPolicy.getBytes("utf-8");
            String encodedPolicy = BinaryUtil.toBase64String(binaryData);
            String postSignature = client.calculatePostSignature(postPolicy);
            // @formatter:off
            signatureDTO=SignatureDTO.builder()
                                                            .accessid(accessId)
                                                            .policy(encodedPolicy)
                                                            .signature(postSignature)
                                                            .dir(dir)
                                                            .host(host)
                                                            .expire(String.valueOf(expireEndTime / 1000))
                                                            .build();
            // @formatter:on
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            client.shutdown();
        }
        return signatureDTO;
    }

}