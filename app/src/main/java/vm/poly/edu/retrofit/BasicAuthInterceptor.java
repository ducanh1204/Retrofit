package vm.poly.edu.retrofit;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class BasicAuthInterceptor implements Interceptor {
    public BasicAuthInterceptor() {
    }
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request authenticatedRequest = request.newBuilder()
                .header("Authorization","Bearer "+"Mkhk_faxmFqF8yG0AWqcKd8sPE-heKXNEdFml3NT9TOFXB27qRuzwDeZDxEKyOirUYctYCfsXRwEH3sX-ygPVqNWBJUhkLbRnloDxbPEsnMc3kKBSNHKFCqdlK4csSiS6v90TqkmnR6LkJ5i6sA5nbijS8xmRHdrHIGg1jFI1BaV6uqnkG7xnO32EB2JFJt8nQ2cmFceUjXljbjCiApaSS7mq1gHfAGZjUo7j-H9EMnzwjyPenkT1R6iYthVNGLPHzeR9WMcuthMBxvOyAvzBMgsfOKObYS-GflojA37hzTxIbSijIEj_JccB_95aYXnslh8hCDXv3q50PSmQt2bqlYn5onGy1nimFcmJuj3P1wxoduQ-AayFAhaHUv8oAr7-qezrCVHtu1TdM2ee77-oPuOXhrHkSTCCXqDl-gHtZ2AHoJSDv7JwuzJyDIzR80VM_ZvBw_XLoVDaRkhnACokjGkKhSCZW4noLJ-xWaMV3py4lpgBbzMd1Jyd29PbO0JumK4Bw7YsHZCd084RDZExwsdmhQxQAFi_xCiQIp9NuhA4t0V0Jf9gmrxknUfjRuCdToW7ckyAH1CeMvhPyZ2R_LGXjvDCDMsk0B0N7oKcNX2lClzx0X7OZyl8bzmKJRCuxY-w3DGJD0bHZ1LDO5sy7B-41iY49TsxQdm-k64KfU7A2xA3D6M1KZKGJoM1KtVW10pRXv6V7tQDKyaIlCg_6wxklhRZJogFeR2nEl5mK9XkxX8gY2KON46fR13_0lCgUY6CUPKhMAMDdxUwl-gQ9i6B09zeNlCHz8_QzYiAnZzEfxyrYIJYnNcrbYnn51_").build();
        return chain.proceed(authenticatedRequest);
    }
}
